package com.br.marcus.saborfy.domain.finance.enums;

import lombok.Getter;

@Getter
public enum FinanceType {
    EXPENSE("expense"),
    REVENUE("revenue");

    private final String type;

    FinanceType(String type) {
        this.type = type;
    }
}
