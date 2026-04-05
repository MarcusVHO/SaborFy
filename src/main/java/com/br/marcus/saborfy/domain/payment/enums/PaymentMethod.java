package com.br.marcus.saborfy.domain.payment.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    PIX("pix"),
    CASH("cash"),
    DEBIT_CARD("debit_card"),
    CREDIT_CARD("credit_card");

    private final String method;
    PaymentMethod(String method){this.method = method;}

}
