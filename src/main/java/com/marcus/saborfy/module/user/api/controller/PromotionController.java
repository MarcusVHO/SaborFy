package com.marcus.saborfy.module.user.api.controller;

import com.marcus.saborfy.module.user.dto.request.AddRoleRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.service.UserService;
import com.marcus.saborfy.shared.security.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class PromotionController {

    private final UserService service;

    public PromotionController(UserService service) {
        this.service = service;
    }

    @Operation(
            summary = "Change role of a user",
            description = "Create a new user. "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Role added successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            ),
    })
    @PatchMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> changeRole(
            @AuthenticationPrincipal CurrentUser user,
            @Valid @PathVariable Long id,
            @RequestBody @Valid AddRoleRequest request
            ) {
        return ResponseEntity.ok().body(
                service.addRole(
                    user.getHighestRole(),
                    id,
                    request.roleName()
                )
        );
    }

}
