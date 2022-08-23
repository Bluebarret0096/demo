package com.DemoTest.demo.exception;

public class RequiredFieldValidationException extends RuntimeException {

    public RequiredFieldValidationException() {
        super();
    }

    public RequiredFieldValidationException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public RequiredFieldValidationException(String errorMessage) {
        super(errorMessage);
    }

    public RequiredFieldValidationException(Throwable throwable) {
        super(throwable);
    }
}
