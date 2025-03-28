
package com.cartservice.controller;

import java.util.List;

import java.util.Random;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cartservice.model.CartCoupon;
import com.cartservice.service.CartCouponServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping( "/cart/cartcoupon")
@CrossOrigin(origins="*")
public class CartCouponController {

    @Autowired
    CartCouponServiceImpl cartCouponService;

    // Add a new cart coupon
//    @PostMapping(value = "/add")
//    public ResponseEntity<String> insertDetails(@Valid @RequestBody CartCoupon cartCoupon) {
//        cartCoupon.setQuantity(1);  // Set default quantity to 1
//        cartCouponService.add(cartCoupon);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Coupon added successfully");
//    }
    @PostMapping(value = "/add")
    public ResponseEntity<String> insertDetails(@Valid @RequestBody CartCoupon cartCoupon) {
        if (cartCoupon.getCouponId() == null) {
            cartCoupon.setCouponId(generateId());  // Generate and set the id
        }
        cartCoupon.setQuantity(1);  // Set default quantity to 1
        cartCouponService.add(cartCoupon);
        return ResponseEntity.status(HttpStatus.CREATED).body("Coupon added successfully");
    }

    private String generateId() {
        // Implement your ID generation logic here
        return new Random().toString();
    }


    // Get all cart coupons
    @GetMapping(value = "/list")
    public ResponseEntity<List<CartCoupon>> getCartCouponDetails() {
        List<CartCoupon> list = cartCouponService.findAll();
        return ResponseEntity.ok(list);
    }

    // Find a cart coupon by ID
    @GetMapping(value = "/findById/{couponId}")
    public ResponseEntity<CartCoupon> findById(@PathVariable("couponId") String couponId) {
        CartCoupon cartCoupon = cartCouponService.findById(couponId);
        if (cartCoupon == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cartCoupon);
    }

    // Delete a cart coupon by ID
    @DeleteMapping(value = "/deleteById/{couponId}")
    public ResponseEntity<String> deleteById(@PathVariable("couponId") String couponId) {
        cartCouponService.deleteById(couponId);
        return ResponseEntity.ok("Deleted Successfully");
    }

    // Update a cart coupon by ID
    @PutMapping("/updateById/{couponId}")
    public ResponseEntity<CartCoupon> updateCoupon(@PathVariable String couponId, @RequestBody CartCoupon cartCoupon) {
        CartCoupon updatedCoupon = cartCouponService.updateCoupon(couponId, cartCoupon);
        if (updatedCoupon == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedCoupon);
    }

    // Change quantity of a cart coupon
    @PutMapping("changeQuantity/{couponId}/{quantity}")
    public ResponseEntity<String> changeQuantityCoupon(@PathVariable("couponId") String couponId, @PathVariable("quantity") int quantity) {
        if (quantity <= 0) {
            return ResponseEntity.badRequest().body("Quantity must be greater than 0");
        }
        cartCouponService.changequantity(couponId, quantity);
        return ResponseEntity.ok("Quantity updated successfully");
    }

    // Delete all cart coupons
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        cartCouponService.deleteAll();
        return ResponseEntity.ok("All Coupons Deleted Successfully");
    }
    
} 