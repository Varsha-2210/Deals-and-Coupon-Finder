package com.cartservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartservice.exception.CartCouponAlreadyExistsException;
import com.cartservice.exception.CartCouponNotFoundException;
import com.cartservice.model.CartCoupon;
import com.cartservice.repository.CartCouponRepository;
import com.cartservice.repository.CouponRepository;
 

@Service
public class CartCouponServiceImpl implements CartCouponService 
{
	 // Initializing logger
	Logger logger=LoggerFactory.getLogger(CartCouponServiceImpl.class);
	
	@Autowired
	private CartCouponRepository cartCouponRepository;
	@Autowired
    private CouponRepository couponRepository;

	// Add a new cart coupon
	 public CartCoupon add(CartCoupon cartCoupon) {
	        // Check if the coupon exists in the Coupon table
	        if (!couponRepository.existsById(cartCoupon.getCouponId())) {
	            logger.error("Coupon with ID {} does not exist", cartCoupon.getCouponId());
	            throw new CartCouponNotFoundException("Coupon with ID " + cartCoupon.getCouponId() + " does not exist in the system.");
	        }

	        // Check if coupon already exists in the cart
	        if (cartCouponRepository.existsById(cartCoupon.getCouponId())) {
	            logger.error("Coupon with ID {} already exists in the cart", cartCoupon.getCouponId());
	            throw new CartCouponAlreadyExistsException("Coupon with ID " + cartCoupon.getCouponId() + " already exists in the cart.");
	        }

	        // Log adding new coupon
	        logger.info("Adding new coupon with ID {}", cartCoupon.getCouponId());
	        cartCoupon.setQuantity(1); // Default quantity to 1
	        return cartCouponRepository.save(cartCoupon);
	    }
	
	// Find a cart coupon by ID
	public CartCoupon findById(String couponId) {
	    return cartCouponRepository.findById(couponId)
	        .orElseThrow(() -> {
	            logger.error("Coupon with ID {} not found in the cart", couponId);
	            return new CartCouponNotFoundException("Coupon with ID " + couponId + " not found in the cart");
	        });
	}


	// Retrieve all cart coupons
	public List<CartCoupon> findAll() {
		List<CartCoupon> list = cartCouponRepository.findAll();
		List<CartCoupon> list1=new ArrayList<>();
		for(int i =0 ; i<list.size();i++)
		{
			if(list.get(i).getOffer()!=null)
			{
				list1.add(list.get(i));
			}
		}
		// Log the number of found coupons
		logger.info("Found {} coupons in the cart", list1.size());
		return list1;
	}

	// Find a cart coupon by ID
	
 
	// Delete a cart coupon by ID
	public void deleteById(String couponId) 
	{
		 if (!cartCouponRepository.existsById(couponId)) {
			 logger.error("Coupon with ID {} not found in the cart", couponId);
		        throw new CartCouponNotFoundException("Coupon with ID " + couponId + " not found in the cart");
		  }
		    // Log deleting coupon
		    logger.info("Deleting coupon with ID {}", couponId);
		    cartCouponRepository.deleteById(couponId);
	}
	
	 // Update a cart coupon by ID
	 public CartCoupon updateCoupon(String couponId, CartCoupon cartCoupon) {
	        if (!cartCouponRepository.existsById(couponId)) {
	        	logger.error("Coupon with ID {} not found in the cart", couponId);
	            throw new CartCouponNotFoundException("Coupon with ID " + couponId + " not found in the cart");
	        }
	        
	        CartCoupon existingCoupon = findById(couponId);
 
	        existingCoupon.setCategory(cartCoupon.getCategory());
	        existingCoupon.setOffer(cartCoupon.getOffer());
	        existingCoupon.setCompanyName(cartCoupon.getCompanyName());
	        existingCoupon.setExpiryDate(cartCoupon.getExpiryDate());

	        // Log updating coupon
	        logger.info("Updating coupon with ID {}", couponId);
	        return cartCouponRepository.save(existingCoupon);
	  }
	 
	 // Change quantity of a cart coupon by ID
	 public void changequantity(String couponId, int quantity) {
	        CartCoupon cartCoupon = findById(couponId);
	        if (cartCoupon == null) {
	        	logger.error("Coupon with ID {} not found in the cart", couponId);
	            throw new CartCouponNotFoundException("Coupon with ID " + couponId + " not found in the cart");
	        }
	        // Log changing quantity of coupon
	        logger.info("Changing quantity of coupon with ID {} to {}", couponId, quantity);
	        cartCoupon.setQuantity(quantity);
	        cartCouponRepository.save(cartCoupon);
	    }
	 
	 public void deleteAll() {
	        cartCouponRepository.deleteAll();
	    }
	
}