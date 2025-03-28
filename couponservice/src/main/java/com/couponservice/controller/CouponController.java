package com.couponservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.couponservice.exception.CouponAlreadyExistsException;
import com.couponservice.exception.CouponNotFoundException;
import com.couponservice.model.Coupon;
import com.couponservice.service.CouponServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/coupons")
@CrossOrigin(origins="*")
public class CouponController {

    @Autowired
    private CouponServiceImpl couponService;

    // ✅ Add a new coupon
    @PostMapping("/add")
    public ResponseEntity<String> createCoupon(@RequestBody @Valid Coupon coupon) {
        try {
            couponService.add(coupon);
            return ResponseEntity.ok("Coupon with ID " + coupon.getCouponId() + " added successfully.");
        } catch (CouponAlreadyExistsException ex) {
            throw new CouponAlreadyExistsException("Coupon with ID " + coupon.getCouponId() + " already exists.");
        }
    }

    // ✅ Retrieve all coupons
    @GetMapping("/findAll")
    public List<Coupon> getAllCoupons() {
        return couponService.findAll();
    }

    // ✅ Retrieve a coupon by its ID
    @GetMapping("/findById/{couponId}")
    public ResponseEntity<?> getCouponById(@PathVariable String couponId) {
        try {
            Coupon coupon = couponService.findByCouponId(couponId);
            return ResponseEntity.ok(coupon);
        } catch (CouponNotFoundException ex) {
            throw new CouponNotFoundException("Coupon with ID " + couponId + " not found.");
        }
    }

    // ✅ Update a coupon by its ID
    @PutMapping("/updateById/{couponId}")
    public ResponseEntity<String> updateCouponById(@PathVariable String couponId, @RequestBody @Valid Coupon coupon) {
        try {
            couponService.updateCoupon(couponId, coupon);
            return ResponseEntity.ok("Coupon with ID " + couponId + " updated successfully.");
        } catch (CouponNotFoundException ex) {
            throw new CouponNotFoundException("Coupon with ID " + couponId + " not found.");
        }
    }
  
 
    // ✅ Delete a coupon by its ID
    @DeleteMapping("/deleteById/{couponId}")
    public ResponseEntity<String> deleteCoupon(@PathVariable String couponId) {
        try {
            couponService.deleteById(couponId);
            return ResponseEntity.ok("Coupon with ID " + couponId + " has been deleted.");
        } catch (CouponNotFoundException ex) {
            throw new CouponNotFoundException("Coupon with ID " + couponId + " not found.");
        }
    }
}
