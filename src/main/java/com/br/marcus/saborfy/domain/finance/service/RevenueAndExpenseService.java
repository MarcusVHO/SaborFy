package com.br.marcus.saborfy.domain.finance.service;

import java.math.BigDecimal;
import java.time.Instant;

public interface RevenueAndExpenseService {
    BigDecimal getRevenue(Instant startDate, Instant endDate);
    BigDecimal getExpense(Instant startDate, Instant endDate);
    BigDecimal getAvg(Instant startDate, Instant endDate);

}
