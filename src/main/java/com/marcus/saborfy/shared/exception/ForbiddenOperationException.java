package com.marcus.saborfy.shared.exception;

public class ForbiddenOperationException extends RuntimeException {
    public ForbiddenOperationException() {
        super("You dont have permission for this operation");
    }
}
