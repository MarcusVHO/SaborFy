package com.br.marcus.saborfy.domain.payment.service.impl;

import com.br.marcus.saborfy.domain.payment.dto.response.DashboardResponse;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.repository.PaymentRepository;
import com.br.marcus.saborfy.domain.payment.service.DashboardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final PaymentRepository paymentRepository;

    public DashboardServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public DashboardResponse getDashboard(Instant start, Instant endDate) {
        BigDecimal Revenue = paymentRepository.getRevenueBetween(start, endDate);

        BigDecimal total = paymentRepository.getTotalLast30Days(Instant.now().minus(30, ChronoUnit.DAYS));
        BigDecimal avg = total.divide(BigDecimal.valueOf(30), RoundingMode.HALF_UP);

        List<Payment> recent = paymentRepository.findTop10ByOrderByPaidAtDesc();
        List<Object[]> last7 = paymentRepository.getSalesLast7Days();

        return new DashboardResponse(recent, avg, Revenue, last7);
    }
}
