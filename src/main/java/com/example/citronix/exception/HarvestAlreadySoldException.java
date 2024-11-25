package com.example.citronix.exception;

public class HarvestAlreadySoldException extends RuntimeException{
    public HarvestAlreadySoldException(String message) {
        super(message);
    }
}
