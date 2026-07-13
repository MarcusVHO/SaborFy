package com.br.marcus.saborfy.domain.menu.query;

import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.repository.MenuRepository;
import com.br.marcus.saborfy.exceptions.MenuNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MenuFinder {
    private final MenuRepository menuRepository;

    public MenuFinder(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu findEntityById(Long id) {
        return menuRepository.findById(id).orElseThrow(MenuNotFoundException::new);
    }
}
