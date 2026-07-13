package com.br.marcus.saborfy.domain.menu.dto.response;


import java.time.Instant;
import java.util.List;

public record MenuResponse  (
    Long id,
    String name,
    List<ItemMenuResponse> items,
    String createdBy,
    String latestUpdateBy,
    Instant createdAt
){}
