package com.vinsguru.customerportfolio.exceptions;

public class InsufficientSharesException extends RuntimeException {

    private static final String MESSAGE = "Insufficient shares for customer [id=%d]";

    public InsufficientSharesException(Integer id) {
        super(MESSAGE.formatted(id));
    }
}
