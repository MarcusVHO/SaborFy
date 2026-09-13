package com.marcus.saborfy.module.user.finder;

import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.shared.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserFinder {
    private final UserRepository userRepository;

    public UserFinder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findEntityById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findEntityByIdOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
