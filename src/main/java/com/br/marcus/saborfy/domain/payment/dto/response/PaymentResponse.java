package com.br.marcus.saborfy.domain.payment.dto.response;

import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentMethod;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class PaymentResponse {
    private final Long id;
    private final BigDecimal amount;
    private final PaymentMethod method;
    private final PaymentStatus status;
    private Instant paidAt;
    private final Instant createAt;

    public PaymentResponse(Payment payment) {
        this.id = payment.getId();
        this.amount = payment.getAmount();
        this.method = payment.getMethod();
        this.status = payment.getStatus();
        if (payment.getPaidAt() != null) {
            this.paidAt = payment.getPaidAt();
        }
        this.createAt = payment.getCreatedAt();
    }
}
