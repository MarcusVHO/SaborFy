package com.br.marcus.saborfy.domain.menu.dto.response;

import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MenuResponse  {
    private final Long id;
    private final String name;
    private final List<ItemMenuResponse> items;

    public MenuResponse(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();

        this.items = new ArrayList<>();
        for (ItemMenu item : menu.getItems()) {
            ItemMenuResponse newItem = new ItemMenuResponse(item);
            this.items.add(newItem);
        }
    }
}
