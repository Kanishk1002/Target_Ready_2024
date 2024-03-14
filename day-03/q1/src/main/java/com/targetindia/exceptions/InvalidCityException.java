package com.targetindia.exceptions;

public class InvalidCityException extends RuntimeException {
    public InvalidCityException() {
    }

    public InvalidCityException(String message) {
        super(message);
    }

    public InvalidCityException(Throwable cause) {
        super(cause);
    }
}
