package com.cartservice.exception;

//Custom exception class for handling cases where a cart coupon already exists
public class CartCouponAlreadyExistsException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
    //Constructor taking the error message as input
	public CartCouponAlreadyExistsException(String message) {
		// Call the superclass constructor with the error message
		super(message);
	}
}
