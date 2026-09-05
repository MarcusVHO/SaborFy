package com.marcus.saborfy.shared.exception;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException() {
        super("Invalid username or password");
    }
}
