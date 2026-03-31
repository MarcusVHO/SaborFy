package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.repository.MenuRepository;
import com.br.marcus.saborfy.domain.menu.service.ListMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListMenuServiceImpl implements ListMenuService {
    private final MenuRepository menuRepository;


    public ListMenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> menuList() {
        List<Menu> menuList = menuRepository.findAll();
        return menuList;
    }
}
