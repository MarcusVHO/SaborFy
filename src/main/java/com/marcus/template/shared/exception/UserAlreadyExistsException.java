package com.marcus.template.shared.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Username already exists");
    }
}
