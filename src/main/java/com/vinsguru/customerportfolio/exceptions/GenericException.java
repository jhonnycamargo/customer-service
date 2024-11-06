package com.vinsguru.customerportfolio.exceptions;

public class GenericException extends RuntimeException {

    private static final String MESSAGE = "Error occurred for customer [id=%d]";

    public GenericException(Integer id) {
        super(MESSAGE.formatted(id));
    }
}
