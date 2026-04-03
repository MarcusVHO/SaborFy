package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.repository.ItemMenuRepository;
import com.br.marcus.saborfy.domain.menu.repository.MenuRepository;
import com.br.marcus.saborfy.domain.menu.service.CreateItemMenuService;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.exceptions.MenuNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class CreateItemMenuServiceImpl implements CreateItemMenuService {
    private final ItemMenuRepository itemMenuRepository;
    private final MenuRepository menuRepository;

    public CreateItemMenuServiceImpl(ItemMenuRepository itemMenuRepository, MenuRepository menuRepository) {
        this.itemMenuRepository = itemMenuRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public ItemMenu createItemMenu(AuthenticatedUser user, CreateItemMenuRequest request, Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(MenuNotFoundException::new);
        menu.setId(menuId);

        User currentUser = new User();
        currentUser.setId(user.id());

        ItemMenu newItemMenu = new ItemMenu();
        newItemMenu.setMenu(menu);
        newItemMenu.setUser(currentUser);
        newItemMenu.setName(request.name());
        newItemMenu.setPrice(request.price());
        newItemMenu.setDescription(request.description());

        itemMenuRepository.save(newItemMenu);
        return newItemMenu;
    }

}
