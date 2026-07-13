package com.br.marcus.saborfy.domain.menu.dto.response;
import java.math.BigDecimal;
import java.time.Instant;

public record ItemMenuResponse (
     Long id,
     String name,
     BigDecimal price,
     String description,
     String createdBy,
     String latestUpdateBy,
     Instant createdAt
) {}

