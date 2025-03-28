package com.dealservice.exception;
//Custom exception class to represent the scenario when a deal already exists.
public class DealAlreadyExistsException extends RuntimeException {
	 /**
     * Constructs a new DealAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DealAlreadyExistsException(String message) {
        super(message);
    }

   
}
