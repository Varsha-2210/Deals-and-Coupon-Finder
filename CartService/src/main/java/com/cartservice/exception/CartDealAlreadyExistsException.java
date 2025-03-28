package com.cartservice.exception;
//Custom exception class for handling cases where a cart deal already exists
public class CartDealAlreadyExistsException extends RuntimeException {
	// Unique identifier for serialization
	private static final long serialVersionUID = 1L;
	
	// Constructor taking the error message as input
	public CartDealAlreadyExistsException(String message) {
		// Call the superclass constructor with the error message
		super(message);
		
	}
}
