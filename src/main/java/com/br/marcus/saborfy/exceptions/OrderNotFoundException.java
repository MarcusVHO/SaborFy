package com.br.marcus.saborfy.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {super("Order not found!");}
}
