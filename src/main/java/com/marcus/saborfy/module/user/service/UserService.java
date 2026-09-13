package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.shared.exception.ForbiddenOperationException;
import com.marcus.saborfy.shared.exception.RoleNotFoundException;
import com.marcus.saborfy.shared.exception.UserAlreadyExistsException;
import com.marcus.saborfy.shared.exception.UserNotFoundException;
import com.marcus.saborfy.module.user.dto.request.RegisterUserRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.entity.Role;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.RoleRepository;
import com.marcus.saborfy.module.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserService  {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserMapper mapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    public UserResponse register(RoleName currentUserRole, RegisterUserRequest request, Long companyId) {
        if (repository.existsByRegistration(request.registration())) {
            throw new UserAlreadyExistsException();
        }
        validateRolePermission(currentUserRole, request.role());
        User user = User.create(
                companyId,
                request.registration(),
                passwordEncoder.encode(request.password()),
                request.name(),
                roleRepository.findByName(request.role()).orElseThrow(RoleNotFoundException::new)
        );
        User savedUser = repository.save(user);
        return mapper.entityToUserResponse(savedUser);
    }

    public UserResponse addRole(RoleName currentUserRole,Long userId, RoleName newRole) {
        User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        validateRolePermission(currentUserRole, newRole);
        Role role = roleRepository.findByName(newRole).orElseThrow(RoleNotFoundException::new);
        validateRolePermission(currentUserRole, user.getRole().getName());
        user.addRole(role);
        repository.save(user);
        return mapper.entityToUserResponse(user);
    }

    private void validateRolePermission(
            RoleName currentUserRole,
            RoleName newRole
    ) {
        if (currentUserRole.canManager(newRole)) {
            throw new ForbiddenOperationException();
        }
    }

    public Page<UserResponse> getPageUser(String searchText, RoleName roleName, Long restaurantId, Pageable pageable) {
        searchText = searchText == null ? "" : searchText;
        Long roleId = roleName != null
                ? Objects.requireNonNull(roleRepository.findByName(roleName).orElse(null)).getId()
                : null;
        return repository.findAllUsers(searchText, roleId, pageable, restaurantId);
    }
}
