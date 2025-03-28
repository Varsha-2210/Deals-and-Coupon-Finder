package com.couponservice.repository;

import com.couponservice.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String> {
    // Additional query methods can be defined here if needed
}
