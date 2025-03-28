package com.couponservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponservice.exception.CouponAlreadyExistsException;
import com.couponservice.exception.CouponNotFoundException;
import com.couponservice.model.Coupon;
import com.couponservice.repository.CouponRepository;

@Service
public class CouponServiceImpl implements CouponService {

    Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Autowired
    CouponRepository couponRepository;

    // Adds a new coupon to the system.
    public Coupon add(Coupon coupon) {
        if (couponRepository.existsById(coupon.getCouponId())) {
            String errorMessage = "Coupon with ID " + coupon.getCouponId() + " already exists.";
            logger.error(errorMessage);
            throw new CouponAlreadyExistsException(errorMessage);
        }

        Coupon savedCoupon = couponRepository.save(coupon);
        logger.info("New coupon added with ID: {}", savedCoupon.getCouponId());
        return savedCoupon;
    }

    // Deletes a coupon by its ID.
    public void deleteById(String couponId) {
        if (!couponRepository.existsById(couponId)) {
            String errorMessage = "Coupon with ID " + couponId + " is not found.";
            logger.error(errorMessage);
            throw new CouponNotFoundException(errorMessage);
        }

        couponRepository.deleteById(couponId);
        logger.info("Coupon deleted with ID: {}", couponId);
    }

    // Retrieves a list of all coupons in the system.
    public List<Coupon> findAll() {
        List<Coupon> list = couponRepository.findAll();
        logger.info("All coupons retrieved");
        return list;
    }

    // Finds a coupon by its ID.
    public Coupon findByCouponId(String couponId) {
        Optional<Coupon> couponOptional = couponRepository.findById(couponId);
        if (couponOptional.isPresent()) {
            logger.info("Coupon retrieved by ID: {}", couponId);
            return couponOptional.get();
        } else {
            String errorMessage = "Coupon with ID " + couponId + " is not found.";
            logger.error(errorMessage);
            throw new CouponNotFoundException(errorMessage);
        }
    }

    // Updates a coupon identified by its ID with the provided coupon details.
    public Coupon updateCoupon(String couponId, Coupon coupon) {
        if (!couponRepository.existsById(couponId)) {
            String errorMessage = "Coupon with ID " + couponId + " is not found.";
            logger.error(errorMessage);
            throw new CouponNotFoundException(errorMessage);
        }

        Coupon existingCoupon = findByCouponId(couponId);

        existingCoupon.setCouponId(coupon.getCouponId());
        existingCoupon.setCategory(coupon.getCategory());
        existingCoupon.setPrice(coupon.getPrice());
        existingCoupon.setOffer(coupon.getOffer());
        existingCoupon.setCompanyName(coupon.getCompanyName());
        existingCoupon.setExpiryDate(coupon.getExpiryDate());

        Coupon updatedCoupon = couponRepository.save(existingCoupon);
        logger.info("Coupon updated with ID: {}", couponId);
        return updatedCoupon;
    }
}
