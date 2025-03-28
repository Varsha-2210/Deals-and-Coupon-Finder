package com.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cartservice.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String> {
}

