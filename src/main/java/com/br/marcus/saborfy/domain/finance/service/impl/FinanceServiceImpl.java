package com.br.marcus.saborfy.domain.finance.service.impl;

import com.br.marcus.saborfy.domain.finance.dto.response.FinanceResponse;
import com.br.marcus.saborfy.domain.finance.enums.FinanceType;
import com.br.marcus.saborfy.domain.finance.repository.ExpenseRepository;
import com.br.marcus.saborfy.domain.finance.service.FinanceService;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {
    private final PaymentRepository paymentRepository;
    private final ExpenseRepository expenseRepository;

    public FinanceServiceImpl(PaymentRepository paymentRepository, ExpenseRepository expenseRepository) {
        this.paymentRepository = paymentRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<FinanceResponse> getFinance(Instant startDate, Instant endDate) {
        List<FinanceResponse> payments = paymentRepository.findALlByPaidAtBetween(startDate, endDate).stream().map(
                this::createFinanceResponseByPayment
        ).toList();
        List<FinanceResponse> expenses = expenseRepository.findAllExpensesFinance(startDate, endDate);

        List<FinanceResponse> result = new ArrayList<>();
        result.addAll(payments);
        result.addAll(expenses);

        result.sort(Comparator.comparing(FinanceResponse::createdAt).reversed());
        return result;
    }

    private FinanceResponse createFinanceResponseByPayment(Payment payment) {
        FinanceType type = null;
        switch (payment.getStatus()) {
            case APPROVED -> type = FinanceType.REVENUE;
            case REFUNDED -> type = FinanceType.REFUNDED;
            case CANCELED -> type = FinanceType.CANCELED;
            case PENDING -> type = FinanceType.PENDING;
        }
        
        return new FinanceResponse(
                String.valueOf(payment.getOrder().getOrderNumber()),
                payment.getAmount(),
                payment.getPaidAt(),
                type
        );
    }
}
