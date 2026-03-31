package com.br.marcus.saborfy.exceptions;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException() {super("Menu not found!");}
    public MenuNotFoundException(String message) {
        super(message);
    }
}
