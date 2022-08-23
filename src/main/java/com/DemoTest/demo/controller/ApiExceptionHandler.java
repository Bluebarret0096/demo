package com.DemoTest.demo.controller;

import com.DemoTest.demo.ApiErrorResponse;
import com.DemoTest.demo.exception.RequiredFieldValidationException;
import com.DemoTest.demo.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundApiException(UserNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setError(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequiredFieldValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleRequiredApiException(RequiredFieldValidationException ex) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setError(ex.getMessage());
        response.setMessage("User can not be null or empty. please verify the user input.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}