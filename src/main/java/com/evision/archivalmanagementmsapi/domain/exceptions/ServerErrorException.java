package com.evision.archivalmanagementmsapi.domain.exceptions;

public class ServerErrorException extends RuntimeException{
    public ServerErrorException(String message){
        super(message);
    }
}
