package com.br.marcus.saborfy.domain.finance.dto.response;

import com.br.marcus.saborfy.domain.finance.enums.FinanceType;

import java.math.BigDecimal;
import java.time.Instant;

public record FinanceResponse(
     String name,
     BigDecimal value,
     Instant createdAt,
     FinanceType type
){
}
