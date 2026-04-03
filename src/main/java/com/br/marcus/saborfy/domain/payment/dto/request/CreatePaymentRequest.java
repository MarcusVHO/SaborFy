package com.br.marcus.saborfy.domain.payment.dto.request;

import com.br.marcus.saborfy.domain.payment.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePaymentRequest (
        @NotNull(message = "Amount is necessary!")BigDecimal amount,
        @NotNull(message = "Payment method is necessary!")PaymentMethod method
        ){
}
