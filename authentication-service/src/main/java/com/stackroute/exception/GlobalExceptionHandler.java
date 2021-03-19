package com.stackroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// To catch all exceptions
@ControllerAdvice
public class GlobalExceptionHandler {

    // takes care of invalid credentials
    @ExceptionHandler(value = InvalidCredentials.class)
    public ResponseEntity<String> invalidCredentials(InvalidCredentials invalidCredentials) {
        System.out.println("invalid");
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.CONFLICT);
    }

}