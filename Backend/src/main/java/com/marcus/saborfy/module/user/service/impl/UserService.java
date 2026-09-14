package com.marcus.saborfy.module.user.service.impl;
import com.marcus.saborfy.module.user.finder.UserFinder;
import com.marcus.saborfy.module.user.dto.request.AddRoleRequest;
import com.marcus.saborfy.module.user.dto.request.ChangePasswordRequest;
import com.marcus.saborfy.shared.exception.*;
import com.marcus.saborfy.module.user.dto.request.RegisterUserRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.entity.Role;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.RoleRepository;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.shared.security.CurrentUser;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService  {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    private final UserFinder finder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserMapper mapper, UserFinder finder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.finder = finder;
    }


    @Transactional
    public void changePasswordUseCase(CurrentUser currentUser, ChangePasswordRequest request) {
        User user = finder.findEntityByIdOrThrow(request.userId());
        validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        if (currentUser.id().equals(request.userId())) {
            changePassword(request.password(), request.newPassword(), user, user);
        } else {
            validateCanManage(currentUser.getHighestRole(), user.getRoleName());
            User userAdmin = finder.findEntityByIdOrThrow(currentUser.id());
            changePassword(request.password(), request.newPassword(), user, userAdmin);
        }
        repository.save(user);
    }

    public void changeEnableUserUseCase(CurrentUser currentUser, Long userId, boolean state) {
        User user = finder.findEntityByIdOrThrow(userId);
        validateCanManage(currentUser.getHighestRole(), user.getRoleName());
        validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        if (state) {
            user.enable();
        } else {
            user.disable();
        }
        repository.save(user);
    }

    public void changeNameUseCase(CurrentUser currentUser, Long userId, String name) {
        User user = finder.findEntityByIdOrThrow(userId);
        validateCanManage(currentUser.getHighestRole(), user.getRoleName());
        validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        user.update(name);
        repository.save(user);
    }

    private void changePassword(String password, String newPassword, User user, User userRequesting) {
        if (
            !user.isActive()
            || !passwordEncoder.matches(password, userRequesting.getPasswordHash())
        ) {
            throw new InvalidCredentialException();
        }
        user.setPasswordHash(passwordEncoder.encode(newPassword.trim()));
    }

    private void validateCanManage(
            RoleName currentUserRole,
            RoleName newRole
    ) {
        if (currentUserRole.canManager(newRole)) {
            throw new ForbiddenOperationException();
        }
    }
    
    private void validateCanAssignRole(
            RoleName currentUserRole,
            RoleName newRole
    ) {
        if (currentUserRole.canManager(newRole)) {
            throw new ForbiddenOperationException();
        }
    }

    private void validateRestaurant(Long currentRestaurantId, Long newRestaurantId) {
        if (!currentRestaurantId.equals(newRestaurantId)) {
            throw new ForbiddenOperationException();
        }
    }
}
