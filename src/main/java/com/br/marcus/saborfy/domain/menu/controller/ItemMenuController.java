package com.br.marcus.saborfy.domain.menu.controller;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.request.UpdateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.response.CreateItemMenuResponse;
import com.br.marcus.saborfy.domain.menu.dto.response.ItemMenuResponse;
import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.service.impl.MenuItemServiceImpl;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/menu/item")
public class ItemMenuController {
    private final MenuItemServiceImpl menuItemService;

    public ItemMenuController(MenuItemServiceImpl menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping
    public ResponseEntity<CreateItemMenuResponse> create(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestBody CreateItemMenuRequest request,
            @Valid @RequestParam @NotNull(message = "It is necessary to reference a menu!") Long menuId
            ) {
        ItemMenu itemMenu = menuItemService.createItemMenu(user, request, menuId);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateItemMenuResponse(itemMenu));
    }


    @DeleteMapping
    public ResponseEntity<Void> delete(
            @Valid @RequestParam @NotNull(message = "It is necessary to reference a menu!") Long menuId,
            @Valid @RequestParam @NotNull(message = "Item id is necessary!") Long itemId
    ) {
        menuItemService.deleteItemMenu(menuId, itemId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<ItemMenuResponse> update(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestParam Long menuId,
            @RequestParam Long id,
            @RequestBody UpdateItemMenuRequest request
    ) {
        ItemMenu itemMenu = menuItemService.updateItemMenu(user, request, id, menuId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ItemMenuResponse(itemMenu));
    }
}
