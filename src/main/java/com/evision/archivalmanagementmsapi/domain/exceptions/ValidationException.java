package com.evision.archivalmanagementmsapi.domain.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}
