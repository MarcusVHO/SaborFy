package com.br.marcus.saborfy.domain.menu.controller;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.request.UpdateMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.response.MenuResponse;
import com.br.marcus.saborfy.domain.menu.service.*;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {

        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> list() {
        return ResponseEntity.ok(menuService.menuList());
    }

    @PostMapping
    public ResponseEntity<MenuResponse> create(@AuthenticationPrincipal AuthenticatedUser user, @Valid @RequestBody CreateMenuRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.create(user, request));
    }

    @PutMapping
    public ResponseEntity<MenuResponse> update(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestBody UpdateMenuRequest request,
            @NotNull @RequestParam Long id

    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.updateMenu(user, request, id));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @NotNull @RequestParam Long id
    ) {
        menuService.deleMenu(id);
        return ResponseEntity.noContent().build();
    }
}
