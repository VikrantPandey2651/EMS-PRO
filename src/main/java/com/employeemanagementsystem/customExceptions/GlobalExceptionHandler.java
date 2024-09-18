package com.employeemanagementsystem.customExceptions;


import com.employeemanagementsystem.customExceptions.exceptions.DuplicateValuePresentException;
import com.employeemanagementsystem.customExceptions.exceptions.ExistingAttendanceException;
import com.employeemanagementsystem.customExceptions.exceptions.ModelNotFoundException;
import com.employeemanagementsystem.customExceptions.exceptions.SundayMondayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName =((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName,message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleModelNotFoundExceptions(ModelNotFoundException ex){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error",ex.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("httpCode",ex.getStatus().value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateValuePresentException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateValuePresentExceptions(DuplicateValuePresentException ex){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("httpcode",ex.getStatus().value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistingAttendanceException.class)
    public ResponseEntity<Map<String,Object>> handleExistingAttendanceException(ExistingAttendanceException ex){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("httpcode",ex.getStatus().value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);


    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SundayMondayException.class)
    public ResponseEntity<Map<String,Object>> handleSundayMondayException(SundayMondayException ex){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("httpcode",ex.getStatus().value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
