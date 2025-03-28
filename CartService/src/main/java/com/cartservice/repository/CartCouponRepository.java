package com.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cartservice.model.CartCoupon;

 // Ensure Coupon model exists

@Repository
public interface CartCouponRepository extends JpaRepository<CartCoupon, String> {
}
