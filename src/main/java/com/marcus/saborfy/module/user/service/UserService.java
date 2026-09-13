package com.marcus.saborfy.module.user.service;

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

import java.util.Objects;

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

    // Register user in system
    @Transactional
    public UserResponse registerUseCase(RoleName currentUserRole, RegisterUserRequest request, Long companyId) {
        if (repository.existsByRegistration(request.registration())) {
            throw new UserAlreadyExistsException();
        }
        validateRolePermission(currentUserRole, request.role());
        User user = User.create(
                companyId,
                request.registration(),
                passwordEncoder.encode(request.password().trim()),
                request.name(),
                roleRepository.findByName(request.role()).orElseThrow(RoleNotFoundException::new)
        );
        User savedUser = repository.save(user);
        return mapper.entityToUserResponse(savedUser);
    }

    //  Register change role of user in system
    @Transactional
    public UserResponse addRoleUseCase(CurrentUser currentUser, Long userId, AddRoleRequest request) {
        User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        validateRolePermission(currentUser.getHighestRole(), request.roleName());
        validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        Role role = roleRepository.findByName(request.roleName()).orElseThrow(RoleNotFoundException::new);
        validateRolePermission(currentUser.getHighestRole(), user.getRoleName());
        user.addRole(role);
        repository.save(user);
        return mapper.entityToUserResponse(user);
    }

    // Get list users in database
    public Page<UserResponse> getPageUserUseCase(String searchText, RoleName roleName, Long restaurantId, Pageable pageable) {
        searchText = searchText == null ? "" : searchText;
        Long roleId = roleName != null
                ? Objects.requireNonNull(roleRepository.findByName(roleName).orElse(null)).getId()
                : null;
        return repository.findAllUsers(searchText, roleId, pageable, restaurantId);
    }

    // Change role of determined user in system
    public void changePasswordUseCase(CurrentUser currentUser, ChangePasswordRequest request) {
        User user = finder.findEntityByIdOrThrow(request.userId());
        validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        if (currentUser.id().equals(request.userId())) {
            changePassword(request.password(), request.newPassword(), user, user);
        } else {
            validateRolePermission(currentUser.getHighestRole(), user.getRoleName());
            User userAdmin = finder.findEntityByIdOrThrow(currentUser.id());
            changePassword(request.password(), request.newPassword(), user, userAdmin);
        }
        System.out.println(passwordEncoder.matches(request.newPassword(), user.getPasswordHash()));
        repository.save(user);

    }

    //Enable and disable user
    public void changeEnableUserUseCase(CurrentUser currentUser, Long userId, boolean state) {
        User user = finder.findEntityByIdOrThrow(userId);
        validateRolePermission(currentUser.getHighestRole(), user.getRoleName());
        validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        if (state) {
            user.enable();
        } else {
            user.disable();
        }
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

    private void validateRolePermission(
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
