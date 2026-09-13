package com.marcus.saborfy.module.finder;

import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.UserRepository;
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
