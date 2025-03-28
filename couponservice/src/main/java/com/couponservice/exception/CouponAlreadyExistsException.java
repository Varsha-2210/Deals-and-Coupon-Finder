package com.couponservice.exception;

public class CouponAlreadyExistsException extends RuntimeException {

   private static final long serialVersionUID = 1L;
	
    // Constructor with message and cause for the exception.
	public CouponAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	// Constructor with message for the exception.
	public CouponAlreadyExistsException(String message) {
		super(message);
	}
}
