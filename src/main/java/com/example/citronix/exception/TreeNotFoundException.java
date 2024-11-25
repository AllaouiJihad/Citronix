package com.example.citronix.exception;

public class TreeNotFoundException extends RuntimeException{
    public TreeNotFoundException(String message) {
        super(message);
    }
}
