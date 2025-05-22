package com.example.bankcards.exception.exceptions;

public class BalanceNotEnoughException extends RuntimeException {
    public BalanceNotEnoughException(String message) {
        super(message);
    }
}
