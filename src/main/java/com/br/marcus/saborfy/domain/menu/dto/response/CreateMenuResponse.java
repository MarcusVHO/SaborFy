package com.br.marcus.saborfy.domain.menu.dto.response;

import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CreateMenuResponse {
    private final Long id;
    private final String name;
    private final LocalDateTime createdAt;

    public CreateMenuResponse(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.createdAt = menu.getCreatedAt();
    }
}
