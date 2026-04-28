package com.br.marcus.saborfy.domain.finance.service;

import com.br.marcus.saborfy.domain.finance.dto.response.FinanceResponse;

import java.time.Instant;
import java.util.List;

public interface FinanceService {
    List<FinanceResponse> getFinance(Instant startDate, Instant endDate);
}
