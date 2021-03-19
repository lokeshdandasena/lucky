package com.stackroute.exception;

public class UserNotFound extends Exception {
    public String str;

    public UserNotFound() {

    }

    public UserNotFound(String str) {
        super();
        this.str = str;
    }
}
