package com.br.marcus.saborfy.domain.payment.dto.request;

import com.br.marcus.saborfy.domain.payment.enums.PaymentMethod;

import java.math.BigDecimal;

public record UpdatePaymentRequest (
        PaymentMethod method,
        BigDecimal amount
) {
}
