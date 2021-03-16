package com.genesys.techtest.exception;

public class InvalidInputException extends RuntimeException{
    private static final long serialVersionUID = 5489516240608806490L;
    public InvalidInputException() {
        super("Invalid Input");
    }
    public InvalidInputException(String message) {
        super(message);
    }
}