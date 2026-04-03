package com.br.marcus.saborfy.domain.payment.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING("pending"),
    APPROVED("approved"),
    FAILED("failed"),
    CANCELED("canceled"),
    REFUNDED("refunded"),
    PARTIALLY_PAID("partially_pai");

    private final String status;
    PaymentStatus(String status) {this.status = status;}

}
