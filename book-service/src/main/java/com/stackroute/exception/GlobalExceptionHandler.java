package com.stackroute.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value(value = "BookNotFound")
    private String message2;


    @ExceptionHandler(value = BookNotFound.class)
    public ResponseEntity<String> BookNotFoundException(BookNotFound BookNotFound) {
        return new ResponseEntity<String>(message2, HttpStatus.CONFLICT);
    }
}
