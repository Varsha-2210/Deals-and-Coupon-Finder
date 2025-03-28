package com.cartservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import com.cartservice.exception.CartCouponAlreadyExistsException;
import com.cartservice.exception.CartCouponNotFoundException;
import com.cartservice.model.CartCoupon;
import com.cartservice.repository.CartCouponRepository;

@ExtendWith(MockitoExtension.class)  // Use MockitoExtension for JUnit 5
public class CartCouponServiceImplTest {

    @Mock
    private CartCouponRepository cartCouponRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private CartCouponServiceImpl cartCouponService;

//    // Test case for adding a coupon successfully
//    @Test
//    public void addCoupon_Success() {
//        CartCoupon cartCoupon = new CartCoupon("123", "Electronics", 100, "10% off", "ABC Inc.", "2025-12-31", 5);
//        when(cartCouponRepository.existsById("123")).thenReturn(false);
//        when(cartCouponRepository.save(cartCoupon)).thenReturn(cartCoupon);
//
//        CartCoupon result = cartCouponService.add(cartCoupon);
//
//        assertEquals(cartCoupon, result);
//        verify(logger).info("Adding new coupon with ID {}", "123");
//    }

   
    // Test case for retrieving all coupons
    @Test
    public void findAllCoupons() {
        List<CartCoupon> allCoupons = new ArrayList<>();
        allCoupons.add(new CartCoupon("123", "Electronics", 100, "10% off", "ABC Inc.", "2025-12-31", 5));
        allCoupons.add(new CartCoupon("456", "Fashion", 50, "20% off", "XYZ Corp.", "2024-12-31", 3));
        when(cartCouponRepository.findAll()).thenReturn(allCoupons);

        List<CartCoupon> result = cartCouponService.findAll();

        assertEquals(2, result.size());
        verify(logger).info("Found {} coupons in the cart", 2);
    }

   
    // Test case for finding a coupon by ID when it does not exist
    @Test
    public void findById_NotFound() {
        when(cartCouponRepository.findById("123")).thenReturn(Optional.empty());
        assertThrows(CartCouponNotFoundException.class, () -> {
            cartCouponService.findById("123");
        });
    }

    // Test case for deleting a coupon by ID
    @Test
    public void deleteById_Success() {
        when(cartCouponRepository.existsById("123")).thenReturn(true);

        cartCouponService.deleteById("123");

        verify(logger).info("Deleting coupon with ID {}", "123");
        verify(cartCouponRepository).deleteById("123");
    }

    // Test case for deleting a coupon by ID when it does not exist
    @Test
    public void deleteById_NotFound() {
        when(cartCouponRepository.existsById("123")).thenReturn(false);
        assertThrows(CartCouponNotFoundException.class, () -> {
            cartCouponService.deleteById("123");
        });
    }

    // Test case for updating a coupon successfully
    @Test
    public void updateCoupon_Success() {
        CartCoupon existingCoupon = new CartCoupon("123", "Electronics", 100, "10% off", "ABC Inc.", "2025-12-31", 5);
        CartCoupon updatedCoupon = new CartCoupon("123", "Electronics", 150, "20% off", "DEF Corp.", "2026-12-31", 10);
        when(cartCouponRepository.existsById("123")).thenReturn(true);
        when(cartCouponRepository.findById("123")).thenReturn(Optional.of(existingCoupon));
        when(cartCouponRepository.save(existingCoupon)).thenReturn(updatedCoupon);

        CartCoupon result = cartCouponService.updateCoupon("123", updatedCoupon);

        assertEquals(updatedCoupon, result);
        verify(logger).info("Updating coupon with ID {}", "123");
    }

    // Test case for updating a coupon when it does not exist
    @Test
    public void updateCoupon_NotFound() {
        when(cartCouponRepository.existsById("123")).thenReturn(false);

        assertThrows(CartCouponNotFoundException.class, () -> {
            cartCouponService.updateCoupon("123", new CartCoupon());
        });
    }

    // Test case for changing the quantity of a coupon successfully
    @Test
    public void changeQuantity_Success() {
        CartCoupon existingCoupon = new CartCoupon("123", "Electronics", 100, "10% off", "ABC Inc.", "2025-12-31", 5);
        when(cartCouponRepository.findById("123")).thenReturn(Optional.of(existingCoupon));

        cartCouponService.changequantity("123", 8);

        assertEquals(8, existingCoupon.getQuantity());
        verify(logger).info("Changing quantity of coupon with ID {} to {}", "123", 8);
        verify(cartCouponRepository).save(existingCoupon);
    }

    // Test case for changing the quantity of a coupon when it does not exist
    @Test
    public void changeQuantity_NotFound() {
        when(cartCouponRepository.findById("123")).thenReturn(Optional.empty());
        assertThrows(CartCouponNotFoundException.class, () -> {
            cartCouponService.changequantity("123", 8);
        });
    }
}
