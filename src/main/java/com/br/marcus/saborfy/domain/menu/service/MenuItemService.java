package com.br.marcus.saborfy.domain.menu.service;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.request.UpdateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface MenuItemService {
    ItemMenu createItemMenu(AuthenticatedUser user, CreateItemMenuRequest request, Long menuId);
    void deleteItemMenu(Long menuId, Long itemId);
    ItemMenu updateItemMenu(@AuthenticationPrincipal AuthenticatedUser user, UpdateItemMenuRequest request, Long itemId, Long menuId);

}
