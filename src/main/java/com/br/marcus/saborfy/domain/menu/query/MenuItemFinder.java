package com.br.marcus.saborfy.domain.menu.query;

import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.repository.ItemMenuRepository;
import com.br.marcus.saborfy.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MenuItemFinder {
    private final ItemMenuRepository repository;

    public MenuItemFinder(ItemMenuRepository repository) {
        this.repository = repository;
    }

    public ItemMenu findEntityById(Long id) {
        return repository.findById(id).orElseThrow(ItemNotFoundException::new);
    }
}
