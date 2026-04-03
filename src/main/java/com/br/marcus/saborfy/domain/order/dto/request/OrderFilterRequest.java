package com.br.marcus.saborfy.domain.order.dto.request;

import com.br.marcus.saborfy.domain.order.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderFilterRequest(
         String name,
         OrderStatus status,
         Long orderNumber,
         String street,
         Integer addressNumber,
         LocalDateTime startDate,
         LocalDateTime endDate
) {
}
