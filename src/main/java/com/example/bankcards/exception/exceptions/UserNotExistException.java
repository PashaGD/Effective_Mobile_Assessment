package com.example.bankcards.exception.exceptions;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String message){
        super(message);
    }
}
