package com.example.citronix.exception;

public class HarvestNotFoundException extends RuntimeException{
    public HarvestNotFoundException(String message) {
        super(message);
    }
}
