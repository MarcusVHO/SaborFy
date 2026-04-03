package com.br.marcus.saborfy.domain.menu.dto.request;

import java.math.BigDecimal;

public record UpdateItemMenuRequest(
        String name,
        BigDecimal price,
        String description
) {
}
