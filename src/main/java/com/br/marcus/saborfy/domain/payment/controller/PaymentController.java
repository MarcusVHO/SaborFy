package com.br.marcus.saborfy.domain.payment.controller;

import com.br.marcus.saborfy.domain.payment.dto.request.CreatePaymentRequest;
import com.br.marcus.saborfy.domain.payment.dto.request.UpdatePaymentRequest;
import com.br.marcus.saborfy.domain.payment.dto.response.PaymentResponse;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.service.CreatePaymentService;
import com.br.marcus.saborfy.domain.payment.service.DeletePaymentService;
import com.br.marcus.saborfy.domain.payment.service.UpdatePaymentService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/payment")
public class PaymentController {

    private final CreatePaymentService createPaymentService;
    private final DeletePaymentService deletePaymentService;
    private final UpdatePaymentService updatePaymentService;

    public PaymentController(CreatePaymentService createPaymentService, DeletePaymentService deletePaymentService, UpdatePaymentService updatePaymentService) {
        this.createPaymentService = createPaymentService;
        this.deletePaymentService = deletePaymentService;
        this.updatePaymentService = updatePaymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestBody CreatePaymentRequest request,
            @NotNull(message = "Order ID is necessary!") Long orderId
    ) {
        Payment payment = createPaymentService.createPayment(user, request, orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PaymentResponse(payment));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @NotNull(message = "Order ID is necessary") Long orderId,
            @NotNull(message = "Payment ID is necessary") Long paymentId
    )
    {
        deletePaymentService.deletePayment(orderId, paymentId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<PaymentResponse> update(
            @RequestBody UpdatePaymentRequest request,
            @RequestParam @NotNull(message = "Order ID is necessary") Long orderId,
            @RequestParam @NotNull(message = "Payment ID is necessary") Long paymentId
    ) {
        Payment payment = updatePaymentService.updatePayment(request, orderId, paymentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PaymentResponse(payment));
    }
}
