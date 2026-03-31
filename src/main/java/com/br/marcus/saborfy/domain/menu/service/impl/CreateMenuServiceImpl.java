package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.repository.MenuRepository;
import com.br.marcus.saborfy.domain.menu.service.CreateMenuService;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class CreateMenuServiceImpl implements CreateMenuService {
    private final MenuRepository menuRepository;

    public CreateMenuServiceImpl(MenuRepository menuRepository) {
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
}
