package com.example.CartFlip.Exceptions;

public class SellerNotFoundException extends RuntimeException{

    public SellerNotFoundException(String message) {
        super(message);
    }
}