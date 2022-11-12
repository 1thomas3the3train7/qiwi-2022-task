package com.example.qiwi2022backend.Exception;

public class IncorrectUserTokenException extends RuntimeException{
    public IncorrectUserTokenException() {
    }

    public IncorrectUserTokenException(String message) {
        super(message);
    }

    public IncorrectUserTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectUserTokenException(Throwable cause) {
        super(cause);
    }
}
