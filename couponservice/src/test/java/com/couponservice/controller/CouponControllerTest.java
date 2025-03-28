package com.couponservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.couponservice.exception.CouponAlreadyExistsException;
import com.couponservice.exception.CouponNotFoundException;
import com.couponservice.model.Coupon;
import com.couponservice.service.CouponServiceImpl;

@ExtendWith(MockitoExtension.class)
class CouponControllerTest {

    @Mock
    private CouponServiceImpl couponService;

    @InjectMocks
    private CouponController couponController;

    // ✅ Test retrieving all coupons
    @Test
    void testGetAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        when(couponService.findAll()).thenReturn(coupons);

        List<Coupon> retrievedCoupons = couponController.getAllCoupons();

        assertEquals(coupons, retrievedCoupons);
    }

    // ✅ Test finding a coupon by ID (Success Case)
    @Test
    void testGetCouponById_Success() {
        String couponId = "123";
        Coupon coupon = new Coupon();
        when(couponService.findByCouponId(couponId)).thenReturn(coupon);

        ResponseEntity<?> response = couponController.getCouponById(couponId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(coupon, response.getBody());
    }

    // ✅ Test finding a coupon by ID (Failure Case)
    @Test
    void testGetCouponById_NotFound() {
        String couponId = "123";
        when(couponService.findByCouponId(couponId)).thenThrow(new CouponNotFoundException("Coupon not found"));

        assertThrows(CouponNotFoundException.class, () -> couponController.getCouponById(couponId));
    }

    // ✅ Test adding a new coupon (Success Case)
    @Test
    void testCreateCoupon_Success() {
        Coupon coupon = new Coupon();
        when(couponService.add(coupon)).thenReturn(coupon);

        ResponseEntity<String> response = couponController.createCoupon(coupon);

        verify(couponService).add(coupon);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Coupon with ID " + coupon.getCouponId() + " added successfully.", response.getBody());
    }

    // ✅ Test adding a new coupon (Failure Case)
    @Test
    void testCreateCoupon_AlreadyExists() {
        Coupon coupon = new Coupon();
        when(couponService.add(coupon)).thenThrow(new CouponAlreadyExistsException("Coupon already exists"));

        assertThrows(CouponAlreadyExistsException.class, () -> couponController.createCoupon(coupon));
    }

    // ✅ Test updating a coupon (Success Case)
    @Test
    void testUpdateCouponById_Success() {
        String couponId = "123";
        Coupon coupon = new Coupon();
        when(couponService.updateCoupon(couponId, coupon)).thenReturn(coupon);

        ResponseEntity<String> response = couponController.updateCouponById(couponId, coupon);

        verify(couponService).updateCoupon(couponId, coupon);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Coupon with ID " + couponId + " updated successfully.", response.getBody());
    }

    // ✅ Test updating a coupon (Failure Case)
    @Test
    void testUpdateCouponById_NotFound() {
        String couponId = "123";
        Coupon coupon = new Coupon();
        when(couponService.updateCoupon(couponId, coupon)).thenThrow(new CouponNotFoundException("Coupon not found"));

        assertThrows(CouponNotFoundException.class, () -> couponController.updateCouponById(couponId, coupon));
    }

    // ✅ Test deleting a coupon (Success Case)
    @Test
    void testDeleteCouponById_Success() {
        String couponId = "123";
        doNothing().when(couponService).deleteById(couponId);

        ResponseEntity<String> response = couponController.deleteCoupon(couponId);

        verify(couponService).deleteById(couponId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Coupon with ID " + couponId + " has been deleted.", response.getBody());
    }

    // ✅ Test deleting a coupon (Failure Case)
    @Test
    void testDeleteCouponById_NotFound() {
        String couponId = "123";
        doThrow(new CouponNotFoundException("Coupon not found")).when(couponService).deleteById(couponId);

        assertThrows(CouponNotFoundException.class, () -> couponController.deleteCoupon(couponId));
    }
}
