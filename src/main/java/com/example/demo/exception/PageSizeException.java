package com.example.demo.exception;

public class PageSizeException extends RuntimeException {
    public PageSizeException(String message) {
        super(message);
    }
}
