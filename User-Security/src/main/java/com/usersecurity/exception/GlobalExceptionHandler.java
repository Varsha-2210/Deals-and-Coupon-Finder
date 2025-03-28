package com.usersecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleAdminNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Handle UserAlreadyPresentException
    @ExceptionHandler(UserAlreadyPresentException.class)
    public ResponseEntity<Object> handleAdminAlreadyExistsException(UserAlreadyPresentException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Handle MethodArgumentNotValidException for validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Get the field error from the exception
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null && fieldError.getDefaultMessage() != null) ? fieldError.getDefaultMessage() : "Validation failed";
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle any other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        // Extract error message from the exception or use a default message
        String errorMessage = (ex != null ? ex.getMessage() : "Internal Server Error");
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
