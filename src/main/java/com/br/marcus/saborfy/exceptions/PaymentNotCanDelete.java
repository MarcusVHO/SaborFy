package com.br.marcus.saborfy.exceptions;

public class PaymentNotCanDelete extends RuntimeException {
    public PaymentNotCanDelete() {
        super("Payment can not delete!");
    }
}
