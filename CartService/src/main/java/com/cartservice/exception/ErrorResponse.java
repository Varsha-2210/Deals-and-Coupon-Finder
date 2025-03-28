package com.cartservice.exception;

import org.springframework.http.HttpStatus;

//Class representing an error response with status and message
public class ErrorResponse {
	// HTTP status code of the error response
    private HttpStatus status;
    // Error message associated with the error response
    private String message;
    
    // Constructor taking HttpStatus and message as input
    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    // Getter method for status
    public HttpStatus getStatus() {
        return status;
    }
    // Setter method for status
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    // Getter method for message
    public String getMessage() {
        return message;
    }
    // Setter method for message
    public void setMessage(String message) {
        this.message = message;
    }
}
