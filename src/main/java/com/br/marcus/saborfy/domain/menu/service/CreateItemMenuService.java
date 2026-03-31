package com.br.marcus.saborfy.domain.menu.service;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

public interface CreateItemMenuService {
    ItemMenu createItemMenu(AuthenticatedUser user, CreateItemMenuRequest request, Long menuId);
}
