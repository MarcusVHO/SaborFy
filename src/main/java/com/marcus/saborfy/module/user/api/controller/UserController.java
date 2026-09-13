package com.marcus.saborfy.module.user.api.controller;

import com.marcus.saborfy.module.user.dto.request.ChangePasswordRequest;
import com.marcus.saborfy.module.user.dto.request.RegisterUserRequest;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.shared.security.CurrentUser;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterUserRequest request,
            @AuthenticationPrincipal CurrentUser user
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.registerUseCase(user.getHighestRole(),request, user.companyId())
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserResponse>> listUsers(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) RoleName roleName,
            Pageable pageable,
            @AuthenticationPrincipal CurrentUser user
            ) {
        return ResponseEntity.ok().body(service.getPageUserUseCase(searchText, roleName, user.companyId(),pageable));
    }

    @PatchMapping("/password")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updatePassword (
            @AuthenticationPrincipal CurrentUser currentUser,
            @Valid @RequestBody ChangePasswordRequest request
            ){
        service.changePasswordUseCase(currentUser, request);
        return null;
    }
}
