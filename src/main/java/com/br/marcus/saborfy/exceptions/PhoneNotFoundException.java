package com.br.marcus.saborfy.exceptions;

public class PhoneNotFoundException extends RuntimeException {
    public PhoneNotFoundException() {super("Phone not found!");}
    public PhoneNotFoundException(String message) {super(message);}
}
