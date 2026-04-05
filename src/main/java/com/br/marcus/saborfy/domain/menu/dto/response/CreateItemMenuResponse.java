package com.br.marcus.saborfy.domain.menu.dto.response;

import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class CreateItemMenuResponse {
        private final Long id;
        private final String name;
        private final BigDecimal price;
        private final String description;
        private final Instant createdAt;


        public CreateItemMenuResponse(ItemMenu menuItem) {
            this.id = menuItem.getId();
            this.name = menuItem.getName();
            this.price = menuItem.getPrice();
            this.description = menuItem.getDescription();
            this.createdAt = menuItem.getCreatedAt();

        }

}
