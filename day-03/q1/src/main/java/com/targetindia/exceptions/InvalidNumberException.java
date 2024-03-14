package com.targetindia.exceptions;

public class InvalidNumberException extends RuntimeException {
    public InvalidNumberException() {
    }

    public InvalidNumberException(String message) {
        super(message);
    }

    public InvalidNumberException(Throwable cause) {
        super(cause);
    }
}
