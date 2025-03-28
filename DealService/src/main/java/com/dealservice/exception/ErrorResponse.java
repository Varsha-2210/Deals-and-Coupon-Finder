package com.dealservice.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

// Represents an error response that can be returned by the RESTful services.

public class ErrorResponse {
	private Date timestamp; // Timestamp of the error occurrence
    private int status; // HTTP status code
    private String error; // HTTP status reason phrase
    private String message; // Error message describing the problem
    private String details; // Additional details about the error

    public ErrorResponse(Date timestamp, HttpStatus status, String message, String details) {
    	this.timestamp = timestamp;
        this.status = status.value(); // Extracts the numeric value of the HttpStatus enum
        this.error = status.getReasonPhrase(); // Retrieves the reason phrase for the given HTTP status code
        this.message = message; // Sets the error message describing the problem
        this.details = details; // Sets additional details about the error
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
