package com.couponservice.exception;

public class CouponNotFoundException extends RuntimeException {

	
private static final long serialVersionUID = 1L;
	
	//Constructor with message and cause for the exception.
	public CouponNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	// Constructor with message for the exception.
	public CouponNotFoundException(String message) {
		super(message);
	}

}
