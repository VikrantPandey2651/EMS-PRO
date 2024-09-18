package com.employeemanagementsystem.customExceptions.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ModelNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public ModelNotFoundException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
