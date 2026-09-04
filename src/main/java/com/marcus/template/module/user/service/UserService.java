package com.marcus.template.module.user.service;

import com.marcus.template.shared.exception.RoleNotFoundException;
import com.marcus.template.shared.exception.UserAlreadyExistsException;
import com.marcus.template.shared.exception.UserNotFoundException;
import com.marcus.template.module.user.dto.request.AddRoleRequest;
import com.marcus.template.module.user.dto.request.RegisterUserRequest;
import com.marcus.template.module.user.dto.response.UserResponse;
import com.marcus.template.module.user.entity.Role;
import com.marcus.template.module.user.entity.User;
import com.marcus.template.module.user.enuns.RoleName;
import com.marcus.template.module.user.mapper.UserMapper;
import com.marcus.template.module.user.repository.RoleRepository;
import com.marcus.template.module.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public UserResponse register(RegisterUserRequest registerUserCommand, Long companyId) {
        log.info("Initializing user register.");
        if (repository.existsByUsername(registerUserCommand.username())) {
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(RoleName.USER).orElseThrow();

        User user = User.create(
                companyId,
                registerUserCommand.username(),
                passwordEncoder.encode(registerUserCommand.password()),
                role
        );
        User savedUser = repository.save(user);
        log.info(
                "User registered successfully. userId={}, companyId={}",
                savedUser.getId(),
                savedUser.getCompanyId()
        );

        return mapper.entityToUserResponse(savedUser);
    }

    public UserResponse addRole(Long userId, AddRoleRequest request) {
        User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        Role role = roleRepository.findByName(request.roleName()).orElseThrow(RoleNotFoundException::new);
        user.addRole(role);
        repository.save(user);
        return mapper.entityToUserResponse(user);
    }
}
