package com.marcus.template.shared.exception;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException() {
        super("Invalid username or password");
    }
}
