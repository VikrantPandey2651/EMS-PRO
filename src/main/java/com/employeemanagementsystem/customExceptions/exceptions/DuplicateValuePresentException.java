package com.employeemanagementsystem.customExceptions.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateValuePresentException extends RuntimeException {

    private final HttpStatus status;

    public DuplicateValuePresentException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
