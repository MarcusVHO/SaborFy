package com.br.marcus.saborfy.domain.menu.service.impl;

import com.br.marcus.saborfy.domain.menu.mapper.MenuMapper;
import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.request.UpdateMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.response.MenuResponse;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.query.MenuFinder;
import com.br.marcus.saborfy.domain.menu.repository.MenuRepository;
import com.br.marcus.saborfy.domain.menu.service.MenuService;
import com.br.marcus.saborfy.exceptions.MenuNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final MenuFinder menuFinder;

    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper, MenuFinder menuFinder) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.menuFinder = menuFinder;
    }

    @Override
    @Transactional
    public MenuResponse create(AuthenticatedUser user, CreateMenuRequest request) {
        Menu newMenu = new Menu(
                request.name(),
                user.id()
        );
        menuRepository.save(newMenu);
        return menuMapper.toResponse(newMenu);
    }

    @Override
    @Transactional
    public void deleMenu(Long menuId) {
        Menu menu = menuFinder.findEntityById(menuId);
        menuRepository.delete(menu);
    }

    @Override
    @Transactional
    public MenuResponse updateMenu(AuthenticatedUser user, UpdateMenuRequest request, Long menuId) {
        Menu menu = menuFinder.findEntityById(menuId);
        menu.changeName(request.name(),  user.id());
        return menuMapper.toResponse(menu);
    }

    @Override
    public List<MenuResponse> menuList() {
        List<Menu> menuList = menuRepository.findAll();
        return menuMapper.toResponseList(menuList);
    }
}
