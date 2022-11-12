package com.example.qiwi2022backend.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User not found (Null)");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
