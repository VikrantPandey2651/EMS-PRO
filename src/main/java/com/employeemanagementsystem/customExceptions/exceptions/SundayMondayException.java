package com.employeemanagementsystem.customExceptions.exceptions;

import org.springframework.http.HttpStatus;

public class SundayMondayException extends RuntimeException{

    private final HttpStatus status;

    public SundayMondayException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
