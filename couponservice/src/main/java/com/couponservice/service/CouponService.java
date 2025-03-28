package com.couponservice.service;

import java.util.List;

import com.couponservice.model.Coupon;

public interface CouponService {
	
	// Adds a new coupon to the system.
	 Coupon add(Coupon coupon) ;
	
	// Deletes a coupon by its ID.
	 void deleteById(String couponId) ;
	 
	// Retrieves a list of all coupons in the system.
	 List<Coupon> findAll() ;
	 
	// Finds a coupon by its ID.
	 Coupon findByCouponId(String couponId);
	 
	 // Updates a coupon identified by its ID with the provided coupon details.
	 Coupon updateCoupon(String couponId, Coupon coupon);
}
