package com.br.marcus.saborfy.domain.menu.dto.response;

import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ItemMenuResponse {
    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final String description;
    private final String createBy;
    private final String latestUpdateBy;
    private final LocalDateTime createdAt;


    public ItemMenuResponse(ItemMenu menuItem) {
        this.id = menuItem.getId();
        this.name = menuItem.getName();
        this.price = menuItem.getPrice();
        this.description = menuItem.getDescription();
        this.createBy = menuItem.getUser().getName();
        this.latestUpdateBy = menuItem.getLatestUpdateBy().getName();
        this.createdAt = menuItem.getCreatedAt();
    }
}
