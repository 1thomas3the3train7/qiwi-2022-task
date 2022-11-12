package com.example.qiwi2022backend.Exception;

public class NotValidRequestIdException extends RuntimeException {
    public NotValidRequestIdException() {
    }

    public NotValidRequestIdException(String message) {
        super(message);
    }

    public NotValidRequestIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidRequestIdException(Throwable cause) {
        super(cause);
    }
}
