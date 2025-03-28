package com.dealservice.exception;
/**
 * Custom exception class to represent the scenario when a deal is not found.
 */
public class DealNotFoundException extends RuntimeException {

    /**
     * Constructs a new DealNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DealNotFoundException(String message) {
        super(message);
    }

   
}
