package com.marcus.saborfy.module.user.service.impl;

import com.marcus.saborfy.module.user.dto.request.RegisterUserRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.RoleRepository;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.module.user.service.UserRegisterService;
import com.marcus.saborfy.shared.exception.RoleNotFoundException;
import com.marcus.saborfy.shared.exception.UserAlreadyExistsException;
import com.marcus.saborfy.shared.security.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    private final PermissionValidator validator;

    public UserRegisterServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserMapper mapper, PermissionValidator validator) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public UserResponse registerUseCase(CurrentUser currentUser, RegisterUserRequest request) {
        if (repository.existsByRegistration(request.registration())) {
            throw new UserAlreadyExistsException();
        }
        validator.validateCanManage(currentUser.getHighestRole(), request.role());
        User user = User.create(
                currentUser.companyId(),
                request.registration(),
                passwordEncoder.encode(request.password().trim()),
                request.name(),
                roleRepository.findByName(request.role()).orElseThrow(RoleNotFoundException::new)
        );
        User savedUser = repository.save(user);
        return mapper.entityToUserResponse(savedUser);
    }
}
