package com.example.qiwi2022backend.Exception;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException() {
        super("Shop not found");
    }

    public ShopNotFoundException(String message) {
        super(message);
    }

    public ShopNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopNotFoundException(Throwable cause) {
        super(cause);
    }
}
