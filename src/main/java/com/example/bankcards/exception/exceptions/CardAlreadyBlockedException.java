package com.example.bankcards.exception.exceptions;

public class CardAlreadyBlockedException extends RuntimeException {
    public CardAlreadyBlockedException(String message) {
        super(message);
    }
}
