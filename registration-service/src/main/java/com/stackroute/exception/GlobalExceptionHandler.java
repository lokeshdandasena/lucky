package com.stackroute.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "UserAlreadyExists")
    private String message1;

    @Value(value = "UserNotFound")
    private String message2;

    @ExceptionHandler(value = UserAlreadyExists.class)
    public ResponseEntity<String> UserAlreadyExistsException(UserAlreadyExists userAlreadyExists) {
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UserNotFound.class)
    public ResponseEntity<String> UserNotFoundException(UserNotFound userNotFound) {
        return new ResponseEntity<String>(message2, HttpStatus.CONFLICT);
    }
}
