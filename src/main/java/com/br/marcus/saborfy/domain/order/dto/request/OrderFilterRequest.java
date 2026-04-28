package com.br.marcus.saborfy.domain.order.dto.request;

import com.br.marcus.saborfy.domain.order.enums.OrderStatus;

import java.time.Instant;

public record OrderFilterRequest(
         String name,
         OrderStatus status,
         Long orderNumber,
         String street,
         Integer addressNumber,
         Instant startDate,
         Instant endDate
) {
}
