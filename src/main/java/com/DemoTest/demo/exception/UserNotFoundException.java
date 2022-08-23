package com.DemoTest.demo.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public UserNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
