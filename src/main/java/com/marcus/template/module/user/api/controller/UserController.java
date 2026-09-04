package com.marcus.template.module.user.api.controller;

import com.marcus.template.module.user.dto.request.RegisterUserRequest;
import com.marcus.template.shared.security.CurrentUser;
import com.marcus.template.module.user.dto.request.AddRoleRequest;
import com.marcus.template.module.user.dto.response.UserResponse;
import com.marcus.template.module.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(
        name = "User",
        description = "User route relationship management"
)
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PatchMapping("/add-role/{id}")
    public ResponseEntity<UserResponse> addRole(
            @Valid @PathVariable Long id,
            @Valid @RequestBody AddRoleRequest request
        ) {
        return ResponseEntity.ok().body(service.addRole(id, request));
    }


    @Operation(
            summary = "Create a new user in application",
            description = "Create a new user. "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "User created"
            ),
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register (
            @Valid @RequestBody RegisterUserRequest request,
            @AuthenticationPrincipal CurrentUser user
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.register(request, user.companyId())
        );
    }
}
