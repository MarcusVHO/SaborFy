package com.marcus.template.module.user.service;

import com.marcus.template.module.user.entity.User;
import com.marcus.template.module.user.mapper.UserMapper;
import com.marcus.template.module.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserFinder {
    private final UserRepository userRepository;

    public UserFinder(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
    }

    public User findEntityById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
