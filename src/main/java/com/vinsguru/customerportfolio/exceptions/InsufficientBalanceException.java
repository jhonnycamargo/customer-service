package com.vinsguru.customerportfolio.exceptions;

public class InsufficientBalanceException extends RuntimeException {

    private static final String MESSAGE = "Insufficient balance for customer [id=%d]";

    public InsufficientBalanceException(Integer id) {
        super(MESSAGE.formatted(id));
    }
}
