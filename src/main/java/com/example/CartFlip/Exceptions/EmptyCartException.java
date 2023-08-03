package com.example.CartFlip.Exceptions;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException(String message) {
        super(message);
    }
}