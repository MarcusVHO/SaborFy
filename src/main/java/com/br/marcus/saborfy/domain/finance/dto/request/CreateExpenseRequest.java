package com.br.marcus.saborfy.domain.finance.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateExpenseRequest(
        @NotEmpty(message = "Name is necessary!") String name,
        @NotNull(message = "Value is necessary!")BigDecimal value
        ) {

}
