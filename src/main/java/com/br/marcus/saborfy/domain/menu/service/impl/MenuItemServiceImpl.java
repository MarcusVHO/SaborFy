package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.request.UpdateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.response.ItemMenuResponse;
import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.mapper.MenuItemMapper;
import com.br.marcus.saborfy.domain.menu.query.MenuFinder;
import com.br.marcus.saborfy.domain.menu.query.MenuItemFinder;
import com.br.marcus.saborfy.domain.menu.repository.ItemMenuRepository;
import com.br.marcus.saborfy.domain.menu.service.MenuItemService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemFinder finder;
    private final ItemMenuRepository itemMenuRepository;
    private final MenuFinder menuFinder;
    private final MenuItemMapper mapper;

    public MenuItemServiceImpl(MenuItemFinder finder, ItemMenuRepository itemMenuRepository, MenuFinder menuFinder, MenuItemMapper mapper) {
        this.finder = finder;
        this.itemMenuRepository = itemMenuRepository;
        this.menuFinder = menuFinder;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ItemMenuResponse createItemMenu(AuthenticatedUser user, CreateItemMenuRequest request, Long menuId) {
        Menu menu = menuFinder.findEntityById(menuId);
        ItemMenu newItemMenu = new ItemMenu(
                request.name(),
                request.price(),
                request.description(),
                user.id(),
                menu
        );
        itemMenuRepository.save(newItemMenu);
        return mapper.ToResponse(newItemMenu);
    }

    @Override
    @Transactional
    public void deleteItemMenu(Long menuId, Long itemId) {
        ItemMenu itemMenu = finder.findEntityById(menuId);
        itemMenuRepository.delete(itemMenu);
    }

    @Override
    @Transactional
    public ItemMenuResponse updateItemMenu(AuthenticatedUser user, UpdateItemMenuRequest request, Long itemId, Long menuId) {
        ItemMenu itemMenu = finder.findEntityById(menuId);

        if(request.name() != null) {
            itemMenu.changeName(request.name(), user.id());
        }

        if(request.price() != null) {
            itemMenu.changePrice(request.price(), user.id());
        }

        if(request.description() != null) {
            itemMenu.changeDescription(request.description(), user.id());
        }
        return mapper.ToResponse(itemMenu);
    }
}
