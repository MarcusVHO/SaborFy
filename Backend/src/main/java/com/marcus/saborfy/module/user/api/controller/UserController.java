package com.marcus.saborfy.module.user.api.controller;

import com.marcus.saborfy.module.user.dto.request.ChangePasswordRequest;
import com.marcus.saborfy.module.user.dto.request.RegisterUserRequest;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.service.UserListService;
import com.marcus.saborfy.module.user.service.UserRegisterService;
import com.marcus.saborfy.module.user.service.UserUpdateService;
import com.marcus.saborfy.shared.security.CurrentUser;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
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
    private final UserRegisterService registerService;
    private final UserListService listService;
    private final UserUpdateService updateService;

    public UserController(UserRegisterService registerService, UserListService listService, UserUpdateService updateService) {
        this.registerService = registerService;
        this.listService = listService;
        this.updateService = updateService;
    }


    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterUserRequest request,
            @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                registerService.register(currentUser,request)
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
        return ResponseEntity.ok().body(listService.listRestaurantUsers(searchText, roleName, user.companyId(),pageable));
    }

    @PatchMapping("/password")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updatePassword (
            @AuthenticationPrincipal CurrentUser currentUser,
            @Valid @RequestBody ChangePasswordRequest request
            ){
        updateService.changePassword(currentUser, request);
        return null;
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deactivationUser(
            @AuthenticationPrincipal CurrentUser user,
            @PathVariable Long userId
    ) {
        updateService.changeUserActiveState(user, userId, false);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/enable/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> enableUser(
            @AuthenticationPrincipal CurrentUser user,
            @PathVariable Long userId
    ) {
        updateService.changeUserActiveState(user, userId, true);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateName(
            @AuthenticationPrincipal CurrentUser user,
            @PathVariable Long userId,
            @PathVariable String name
    ) {
        updateService.changeName(user, userId, name);
        return ResponseEntity.noContent().build();
    }
}
