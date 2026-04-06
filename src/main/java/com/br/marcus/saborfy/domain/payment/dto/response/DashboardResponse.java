package com.br.marcus.saborfy.domain.payment.dto.response;

import com.br.marcus.saborfy.domain.payment.entity.Payment;

import java.math.BigDecimal;
import java.util.List;

public record DashboardResponse(List<Payment> recent, BigDecimal avg, BigDecimal revenue, List<Object[]> last7) {
}
