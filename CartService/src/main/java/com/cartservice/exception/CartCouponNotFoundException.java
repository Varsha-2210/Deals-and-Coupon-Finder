package com.cartservice.exception;

//Custom exception class for handling cases where a cart coupon is not found
public class CartCouponNotFoundException extends RuntimeException {
	// Unique identifier for serialization
	private static final long serialVersionUID = 1L;
	
    //Constructor taking the error message as input
	public CartCouponNotFoundException(String message) {
		// Call the superclass constructor with the error message
		super(message);
	}
}
