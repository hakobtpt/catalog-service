package com.catalog.service.exception;

public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException() {
    }

    public BookAlreadyExistsException(String message) {
        super("A book with ISBN " + message + " already exists.");
    }

    public BookAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public BookAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
