package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.dto.request.UpdateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.repository.ItemMenuRepository;
import com.br.marcus.saborfy.domain.menu.service.UpdateItemMenuService;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.exceptions.ItemNotFoundException;
import com.br.marcus.saborfy.exceptions.MenuMismatchException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class UpdateItemMenuServiceImpl implements UpdateItemMenuService {
    private final ItemMenuRepository itemMenuRepository;

    public UpdateItemMenuServiceImpl(ItemMenuRepository itemMenuRepository) {
        this.itemMenuRepository = itemMenuRepository;
    }

    @Override
    public ItemMenu updateItemMenu(AuthenticatedUser user, UpdateItemMenuRequest request, Long itemId, Long menuId) {
        ItemMenu itemMenu = itemMenuRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);

        if(!itemMenu.getMenu().getId().equals(menuId)) {
            throw new MenuMismatchException("Item does not belong to this menu!");
        }
        User currentUser = new User();
        currentUser.setId(user.id());

        if(request.name() != null) {
            itemMenu.setName(request.name());
            itemMenu.setLatestUpdateBy(currentUser);
        }

        if(request.price() != null) {
            itemMenu.setPrice(request.price());
            itemMenu.setLatestUpdateBy(currentUser);
        }

        if(request.description() != null) {
            itemMenu.setDescription(request.description());
            itemMenu.setLatestUpdateBy(currentUser);
        }

        itemMenuRepository.save(itemMenu);
        return itemMenu;
    }
}
