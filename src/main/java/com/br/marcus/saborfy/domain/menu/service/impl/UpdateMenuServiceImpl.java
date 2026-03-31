package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.repository.MenuRepository;
import com.br.marcus.saborfy.domain.menu.service.UpdateMenuService;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.exceptions.MenuNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class UpdateMenuServiceImpl implements UpdateMenuService {
    private final MenuRepository menuRepository;

    public UpdateMenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
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
}
