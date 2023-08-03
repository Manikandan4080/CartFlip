package com.example.CartFlip.Exceptions;

public class InsufficientQuantityException extends RuntimeException{

    public InsufficientQuantityException(String message) {
        super(message);
    }
}

