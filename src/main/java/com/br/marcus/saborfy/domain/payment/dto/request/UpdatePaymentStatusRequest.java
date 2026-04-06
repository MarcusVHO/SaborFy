package com.br.marcus.saborfy.domain.payment.dto.request;

import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public record UpdatePaymentStatusRequest(
        @NotNull(message = "Status is necessary!") PaymentStatus status
) {
}
