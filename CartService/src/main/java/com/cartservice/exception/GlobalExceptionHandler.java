package com.cartservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

//Global exception handler for handling exceptions across the application
@ControllerAdvice
public class GlobalExceptionHandler {

	// Exception handler for CartCouponAlreadyExistsException
    @ExceptionHandler(CartCouponAlreadyExistsException.class)
    public ResponseEntity<Object> handleCartCouponAlreadyExistsException(CartCouponAlreadyExistsException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Exception handler for CartCouponNotFoundException
    @ExceptionHandler(CartCouponNotFoundException.class)
    public ResponseEntity<Object> handleCartCouponNotFoundException(CartCouponNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    // Exception handler for CartDealAlreadyExistsException
    @ExceptionHandler(CartDealAlreadyExistsException.class)
    public ResponseEntity<Object> handleCartDealAlreadyExistsException(CartDealAlreadyExistsException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Exception handler for CartDealNotFoundException
    @ExceptionHandler(CartDealNotFoundException.class)
    public ResponseEntity<Object> handleCartDealNotFoundException(CartDealNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Exception handler for other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        String errorMessage = (ex != null ? ex.getMessage() : "Internal Server Error");
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
