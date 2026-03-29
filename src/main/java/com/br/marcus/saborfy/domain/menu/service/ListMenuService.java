package com.br.marcus.saborfy.domain.menu.service;

import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListMenuService {
    private final MenuRepository menuRepository;


    public ListMenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> menuList() {
        List<Menu> menuList = menuRepository.findAll();
        return menuList;
    }
}
