package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.repository.ItemMenuRepository;
import com.br.marcus.saborfy.domain.menu.service.DeleteItemMenuService;
import com.br.marcus.saborfy.exceptions.ItemNotFoundException;
import com.br.marcus.saborfy.exceptions.MenuMismatchException;
import org.springframework.stereotype.Service;

@Service
public class DeleteItemMenuServiceImpl implements DeleteItemMenuService {
    private final ItemMenuRepository itemMenuRepository;

    public DeleteItemMenuServiceImpl(ItemMenuRepository itemMenuRepository) {
        this.itemMenuRepository = itemMenuRepository;
    }

    @Override
    public void deleteItemMenu(Long menuId, Long itemId) {
        ItemMenu itemMenu = itemMenuRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);

        if(!itemMenu.getMenu().getId().equals(menuId)) {
            throw new MenuMismatchException("Item does not belong to this menu!");
        }

        itemMenuRepository.delete(itemMenu);
    }

}
