package com.br.marcus.saborfy.domain.finance.enums;

import lombok.Getter;

@Getter
public enum FinanceType {
    EXPENSE("expense"),
    REVENUE("revenue"),
    INCOME("income"),
    NEUTRAL("neutral"),
    REFUNDED("refunded"),
    CANCELED("canceled"),
    PENDING("pending");


    private final String type;

    FinanceType(String type) {
        this.type = type;
    }
}
