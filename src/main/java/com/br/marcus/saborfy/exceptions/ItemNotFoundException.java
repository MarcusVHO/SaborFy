package com.br.marcus.saborfy.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {super("Item not found!");}
    public ItemNotFoundException(String message) {
        super(message);
    }
}
