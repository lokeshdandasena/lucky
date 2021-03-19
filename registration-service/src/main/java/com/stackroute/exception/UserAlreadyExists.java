package com.stackroute.exception;

public class UserAlreadyExists extends Exception{
    public String str;

    public UserAlreadyExists() {
    }

    public UserAlreadyExists( String str) {
        super();
        this.str = str;
    }
}
