package com.br.marcus.saborfy.domain.menu.dto.response;

import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MenuResponse  {
    private final Long id;
    private final String name;
    private final List<ItemMenuResponse> items;
    private final String createBy;
    private final String LatestUpdateBy;
    private final Instant createdAt;


    public MenuResponse(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();

        this.items = new ArrayList<>();
        for (ItemMenu item : menu.getItems()) {
            ItemMenuResponse newItem = new ItemMenuResponse(item);
            this.items.add(newItem);
        }

        this.createBy = menu.getUser().getName();
        this.LatestUpdateBy = menu.getLatestUpdateBy().getName();
        this.createdAt = menu.getCreatedAt();
    }
}
