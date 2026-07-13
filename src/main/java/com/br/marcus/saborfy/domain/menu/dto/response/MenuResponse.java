package com.br.marcus.saborfy.domain.menu.dto.response;

import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public record MenuResponse  (
    Long id,
    String name,
    List<ItemMenuResponse> items,
    String createBy,
    String LatestUpdateBy,
    Instant createdAt
){}
