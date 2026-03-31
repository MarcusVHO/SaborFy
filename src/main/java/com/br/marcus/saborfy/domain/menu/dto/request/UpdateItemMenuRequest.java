package com.br.marcus.saborfy.domain.menu.dto.request;

public record UpdateItemMenuRequest(
        String name,
        Double price,
        String description
) {
}
