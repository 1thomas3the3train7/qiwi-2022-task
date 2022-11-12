package com.example.qiwi2022backend.Exception;

public class DealNotFoundException extends RuntimeException{
    public DealNotFoundException() {
        super();
    }

    public DealNotFoundException(String message) {
        super(message);
    }

    public DealNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DealNotFoundException(Throwable cause) {
        super(cause);
    }
}
