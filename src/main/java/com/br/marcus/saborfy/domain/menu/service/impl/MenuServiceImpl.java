package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.repository.MenuRepository;
import com.br.marcus.saborfy.domain.menu.service.MenuService;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.exceptions.MenuNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu create(AuthenticatedUser user, CreateMenuRequest request) {
        User currentUser = new User();
        currentUser.setId(user.id());

        Menu newMenu = new Menu();
        newMenu.setName(request.name());
        newMenu.setUser(currentUser);
        newMenu.setLatestUpdateBy(currentUser);

        menuRepository.save(newMenu);
        return newMenu;
    }

    @Override
    public void deleMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(MenuNotFoundException::new);
        menuRepository.delete(menu);
    }

    @Override
    public Menu updateMenu(AuthenticatedUser user, CreateMenuRequest request, Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(MenuNotFoundException::new);

        User currentUser = new User();
        currentUser.setId(user.id());

        menu.setName(request.name());
        menu.setLatestUpdateBy(currentUser);

        menuRepository.save(menu);
        return menu;
    }

    @Override
    public List<Menu> menuList() {
        List<Menu> menuList = menuRepository.findAll();
        return menuList;
    }
}
