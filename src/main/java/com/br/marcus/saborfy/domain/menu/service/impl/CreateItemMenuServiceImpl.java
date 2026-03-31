package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.repository.ItemMenuRepository;
import com.br.marcus.saborfy.domain.menu.service.CreateItemMenuService;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class CreateItemMenuServiceImpl implements CreateItemMenuService {
    private final ItemMenuRepository itemMenuRepository;

    public CreateItemMenuServiceImpl(ItemMenuRepository itemMenuRepository) {
        this.itemMenuRepository = itemMenuRepository;
    }

    @Override
    public ItemMenu createItemMenu(AuthenticatedUser user, CreateItemMenuRequest request, Long menuId) {
        Menu menu = new Menu();
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
