package com.cartservice.exception;
//Custom exception class for handling cases where a cart deal is not found
public class CartDealNotFoundException extends RuntimeException{
	// Unique identifier for serialization
	private static final long serialVersionUID = 1L;
	
	// Constructor taking the error message as input
	public CartDealNotFoundException(String message) {
		// Call the superclass constructor with the error message
		super(message);
	}

	
}
