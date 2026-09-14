package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.dto.request.AddRoleRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.entity.Role;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.RoleRepository;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.shared.exception.RoleNotFoundException;
import com.marcus.saborfy.shared.exception.UserNotFoundException;
import com.marcus.saborfy.shared.security.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PermissionValidator validation;
    private final UserMapper mapper;

    public UserRoleService(UserRepository repository, RoleRepository roleRepository, PermissionValidator validation, UserMapper mapper) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.validation = validation;
        this.mapper = mapper;
    }

    public UserResponse addRole(CurrentUser currentUser, Long userId, AddRoleRequest request) {
        User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        validation.validateCanAssignRole(currentUser.getHighestRole(), request.roleName());
        validation.validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        Role role = roleRepository.findByName(request.roleName()).orElseThrow(RoleNotFoundException::new);
        validation.validateCanManage(currentUser.getHighestRole(), user.getRoleName());
        user.addRole(role);
        repository.save(user);
        return mapper.entityToUserResponse(user);
    }
}
