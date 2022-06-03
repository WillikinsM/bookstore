package com.will.bookstoreapi.resources.exceptions;

public class DataIntegrityViolationException extends RuntimeException{


    public DataIntegrityViolationException(String message) {
        super(message);

    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);

    }
}