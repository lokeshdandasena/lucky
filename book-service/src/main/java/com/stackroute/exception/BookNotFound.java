package com.stackroute.exception;

public class BookNotFound extends Exception {
    public String error;

    public BookNotFound() {

    }

    public BookNotFound(String error) {
        super();
        this.error = error;
    }
}