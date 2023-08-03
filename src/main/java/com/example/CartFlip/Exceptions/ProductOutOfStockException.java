package com.example.CartFlip.Exceptions;

public class ProductOutOfStockException extends RuntimeException{

    public ProductOutOfStockException(String message) {
        super(message);
    }
}
