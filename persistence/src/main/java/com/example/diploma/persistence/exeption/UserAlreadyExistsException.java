package com.example.diploma.persistence.exeption;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }

}
