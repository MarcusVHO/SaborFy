package com.br.marcus.saborfy.domain.finance.service.impl;

import com.br.marcus.saborfy.domain.finance.repository.ExpenseRepository;
import com.br.marcus.saborfy.domain.finance.service.RevenueAndExpenseService;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import com.br.marcus.saborfy.domain.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class RevenueAndExpenseServiceImpl implements RevenueAndExpenseService {
    private final PaymentRepository paymentRepository;
    private final ExpenseRepository expenseRepository;

    public RevenueAndExpenseServiceImpl(PaymentRepository paymentRepository, ExpenseRepository expenseRepository) {
        this.paymentRepository = paymentRepository;
        this.expenseRepository = expenseRepository;
    }

    public BigDecimal getRevenue(Instant startDate, Instant endDate) {
        return paymentRepository.sumByPeriodAndStatus(startDate, endDate, PaymentStatus.APPROVED);
    }

    public BigDecimal getExpense(Instant startDate, Instant endDate) {
        return expenseRepository.sumByPeriod(startDate, endDate);
    }

    public BigDecimal getAvg(Instant startDate, Instant endDate) {
        return paymentRepository.avgByPeriodAndStatus(startDate, endDate, PaymentStatus.APPROVED);
    }
}
