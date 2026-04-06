package com.br.marcus.saborfy.domain.payment.controller;

import com.br.marcus.saborfy.domain.payment.dto.request.UpdatePaymentStatusRequest;
import com.br.marcus.saborfy.domain.payment.dto.response.PaymentResponse;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import com.br.marcus.saborfy.domain.payment.service.UpdatePaymentStatusService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/payment/status")
public class PymentStatusController {
    private final UpdatePaymentStatusService updatePaymentStatusService;

    public PymentStatusController(UpdatePaymentStatusService updatePaymentStatusService) {
        this.updatePaymentStatusService = updatePaymentStatusService;
    }

    @PatchMapping
    public ResponseEntity<PaymentResponse> update(
            @RequestBody UpdatePaymentStatusRequest request,
            @RequestParam @NotNull(message = "Order ID is necessary!") Long orderId,
            @RequestParam @NotNull(message = "Payment ID is necessary!") Long paymentId
    ) {
        Payment payment = updatePaymentStatusService.updateStatus(request, orderId, paymentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PaymentResponse(payment));
    }
}
