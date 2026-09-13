package com.marcus.saborfy.module.user.api.controller;

import com.marcus.saborfy.module.user.dto.request.AddRoleRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.service.UserService;
import com.marcus.saborfy.shared.security.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class PromotionController {

    private final UserService service;

    public PromotionController(UserService service) {
        this.service = service;
    }

    @PatchMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> changeRole(
            @AuthenticationPrincipal CurrentUser user,
            @Valid @PathVariable Long id,
            @RequestBody @Valid AddRoleRequest request
            ) {
        return ResponseEntity.ok().body(
                service.addRoleUseCase(
                    user.getHighestRole(),
                    id,
                    request.roleName()
                )
        );
    }

}
