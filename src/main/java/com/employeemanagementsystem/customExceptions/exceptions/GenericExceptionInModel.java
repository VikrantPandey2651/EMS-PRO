package com.employeemanagementsystem.customExceptions.exceptions;

import org.springframework.http.HttpStatus;

public class GenericExceptionInModel extends RuntimeException{

    public GenericExceptionInModel(String message){
        super(message);
    }



}
