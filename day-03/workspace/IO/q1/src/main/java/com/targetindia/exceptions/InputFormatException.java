package com.targetindia.exceptions;

public class InputFormatException extends RuntimeException {
    public InputFormatException() {
    }

    public InputFormatException(String message) {
        super(message);
    }

    public InputFormatException(Throwable cause) {
        super(cause);
    }
}
