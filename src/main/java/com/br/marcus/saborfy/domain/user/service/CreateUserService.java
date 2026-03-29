package com.br.marcus.saborfy.domain.user.service;

import com.br.marcus.saborfy.domain.user.dto.request.RegisterUserRequest;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.domain.user.enums.UserRole;
import com.br.marcus.saborfy.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(RegisterUserRequest request) {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRegistration(request.registration());
        newUser.setName(request.name());
        newUser.setRole(UserRole.ADMIN);

        userRepository.save(newUser);
        return newUser;
    }
}
