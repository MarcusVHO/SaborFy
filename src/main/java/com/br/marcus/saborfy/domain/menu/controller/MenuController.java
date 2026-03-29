package com.br.marcus.saborfy.domain.menu.controller;


import com.br.marcus.saborfy.domain.menu.dto.response.MenuResponse;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.service.ListMenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {
    private final ListMenuService listMenuService;

    public MenuController(ListMenuService listMenuService) {
        this.listMenuService = listMenuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> list() {
        List<Menu> menus = listMenuService.menuList();

        List<MenuResponse> menuResponses = new ArrayList<>();
        for (Menu menu : menus) {
            MenuResponse menuResponse = new MenuResponse(menu);
            menuResponses.add(menuResponse);
        }
        return ResponseEntity.ok(menuResponses);
    }


}
