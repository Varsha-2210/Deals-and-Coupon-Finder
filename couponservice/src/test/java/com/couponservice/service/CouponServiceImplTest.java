package com.couponservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import com.couponservice.exception.CouponAlreadyExistsException;
import com.couponservice.exception.CouponNotFoundException;
import com.couponservice.model.Coupon;
import com.couponservice.repository.CouponRepository;

class CouponServiceImplTest {

    @Mock
    CouponRepository couponRepository;
    
    @Mock
    Logger logger;
    
    @InjectMocks
    CouponServiceImpl couponService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test method for verifying the addition of a new coupon.
    @Test
    void testAdd() {
        Coupon coupon = new Coupon();
        coupon.setCouponId("123");
        
        when(couponRepository.existsById(coupon.getCouponId())).thenReturn(false);
        when(couponRepository.save(coupon)).thenReturn(coupon);
        
        Coupon savedCoupon = couponService.add(coupon);
        
        assertEquals(coupon, savedCoupon);
    }

    // Test method for verifying the case where adding a coupon fails due to the existing coupon ID.
    @Test
    void testAddCouponAlreadyExists() {
        Coupon coupon = new Coupon();
        coupon.setCouponId("123");
        
        when(couponRepository.existsById(coupon.getCouponId())).thenReturn(true);
        
        assertThrows(CouponAlreadyExistsException.class, () -> {
            couponService.add(coupon);
        });
    }

    // Test method for verifying the deletion of a coupon by its ID.
    @Test
    void testDeleteById() {
        String couponId = "123";
        
        when(couponRepository.existsById(couponId)).thenReturn(true);
        
        couponService.deleteById(couponId);
        
        verify(couponRepository).deleteById(couponId);
    }

    // Test method for verifying the case where deletion fails due to the coupon ID not found.
    @Test
    void testDeleteByIdNotFound() {
        String couponId = "123";
        
        when(couponRepository.existsById(couponId)).thenReturn(false);
        
        assertThrows(CouponNotFoundException.class, () -> {
            couponService.deleteById(couponId);
        });
    }

    // Test method for verifying the retrieval of all coupons.
    @Test
    void testFindAll() {
        List<Coupon> couponList = new ArrayList<>();
        when(couponRepository.findAll()).thenReturn(couponList);
        
        List<Coupon> retrievedList = couponService.findAll();
        
        assertEquals(couponList, retrievedList);
    }

    // Test method for verifying the retrieval of a coupon by its ID.
    @Test
    void testFindByCouponId() {
        String couponId = "123";
        Coupon coupon = new Coupon();
        coupon.setCouponId(couponId);
        
        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));
        
        Coupon retrievedCoupon = couponService.findByCouponId(couponId);
        
        assertEquals(coupon, retrievedCoupon);
    }

    // Test method for verifying the case where retrieval by coupon ID fails due to the ID not found.
    @Test
    void testFindByCouponIdNotFound() {
        String couponId = "123";
        
        when(couponRepository.findById(couponId)).thenReturn(Optional.empty());
        
        assertThrows(CouponNotFoundException.class, () -> {
            couponService.findByCouponId(couponId);
        });
    }

    // Test method for verifying the update of a coupon.    
    @Test
    void testUpdateCoupon() {
        // Create a sample coupon with its ID
        String couponId = "123";
        Coupon existingCoupon = new Coupon();
        existingCoupon.setCouponId(couponId);
        
        // Create an updated coupon with new values
        Coupon updatedCoupon = new Coupon();
        updatedCoupon.setCouponId(couponId);
        updatedCoupon.setCategory("New Category");
        updatedCoupon.setOffer("New Offer");
        updatedCoupon.setCompanyName("New Company");
        updatedCoupon.setExpiryDate(null);
        
        // Mocking behavior of couponRepository
        when(couponRepository.existsById(couponId)).thenReturn(true);
        when(couponRepository.findById(couponId)).thenReturn(Optional.of(existingCoupon));
        when(couponRepository.save(existingCoupon)).thenReturn(updatedCoupon);
        
        // Call the method to update the coupon
        Coupon result = couponService.updateCoupon(couponId, updatedCoupon);
        
        // Verify that the coupon is updated correctly
        assertEquals(updatedCoupon, result);
        assertEquals(updatedCoupon.getCategory(), result.getCategory());
        assertEquals(updatedCoupon.getOffer(), result.getOffer());
        assertEquals(updatedCoupon.getCompanyName(), result.getCompanyName());
        assertEquals(updatedCoupon.getExpiryDate(), result.getExpiryDate());
        
        // Verify that appropriate logging statements are made
        verify(logger).info("Coupon updated with ID: {}", couponId);
    }

    // Test method for verifying the case where update fails due to the coupon ID not found.
    @Test
    void testUpdateCouponNotFound() {
        String couponId = "123";
        Coupon coupon = new Coupon();
        coupon.setCouponId(couponId);
        
        when(couponRepository.existsById(couponId)).thenReturn(false);
        
        assertThrows(CouponNotFoundException.class, () -> {
            couponService.updateCoupon(couponId, coupon);
        });
    }

}
