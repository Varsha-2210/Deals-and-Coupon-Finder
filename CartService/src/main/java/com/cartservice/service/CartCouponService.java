package com.cartservice.service;

import java.util.List;
import java.util.Optional;

import com.cartservice.model.CartCoupon;

//Interface for CartCoupon service operations
public interface CartCouponService {

	// Add a new cart coupon
    public CartCoupon add(CartCoupon cartCoupon);
    
    // Retrieve all cart coupons
    public List<CartCoupon> findAll();
    
    // Find a cart coupon by ID
    public CartCoupon findById(String id);
    
    // Delete a cart coupon by ID
    public void deleteById(String id);
    
    // Update a cart coupon by ID
    public CartCoupon updateCoupon(String id, CartCoupon cartCoupon);
    
    // Change quantity of a cart coupon by ID
    public void changequantity(String id, int quantity);
    
    public void deleteAll();
}