package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.repository.MenuRepository;
import com.br.marcus.saborfy.domain.menu.service.DeleteMenuService;
import com.br.marcus.saborfy.exceptions.MenuNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteMenuImpl implements DeleteMenuService {
    private final MenuRepository menuRepository;

    public DeleteMenuImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void deleMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(MenuNotFoundException::new);
        menuRepository.delete(menu);
    }
}
