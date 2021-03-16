package com.genesys.techtest.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4041009155673754859L;
    public ResourceNotFoundException() {
        super("requested resource not found");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}