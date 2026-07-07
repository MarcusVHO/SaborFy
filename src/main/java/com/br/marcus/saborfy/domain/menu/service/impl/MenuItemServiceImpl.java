package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.request.UpdateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.repository.ItemMenuRepository;
import com.br.marcus.saborfy.domain.menu.repository.MenuRepository;
import com.br.marcus.saborfy.domain.menu.service.MenuItemService;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.exceptions.ItemNotFoundException;
import com.br.marcus.saborfy.exceptions.MenuMismatchException;
import com.br.marcus.saborfy.exceptions.MenuNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuRepository menuRepository;
    private final ItemMenuRepository itemMenuRepository;

    public MenuItemServiceImpl(MenuRepository menuRepository, ItemMenuRepository itemMenuRepository) {
        this.menuRepository = menuRepository;
        this.itemMenuRepository = itemMenuRepository;
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

    @Override
    public void deleteItemMenu(Long menuId, Long itemId) {
        ItemMenu itemMenu = itemMenuRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);

        if(!itemMenu.getMenu().getId().equals(menuId)) {
            throw new MenuMismatchException("Item does not belong to this menu!");
        }

        itemMenuRepository.delete(itemMenu);
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
