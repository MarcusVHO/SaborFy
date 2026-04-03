package com.br.marcus.saborfy.exceptions;

public class OrderWithFewItemsException extends RuntimeException {
    public OrderWithFewItemsException(){super("Order with few items!");}
}
