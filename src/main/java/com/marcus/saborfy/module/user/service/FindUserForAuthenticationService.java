package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.shared.exception.UserNotFoundException;
import com.marcus.saborfy.module.user.api.internal.dto.AuthenticationUserDataResponse;
import com.marcus.saborfy.module.user.api.internal.contract.UserAuthenticationQuery;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FindUserForAuthenticationService implements UserAuthenticationQuery {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public AuthenticationUserDataResponse findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(mapper::entityToAuthenticationUserDataResponse).orElseThrow(UserNotFoundException::new);
    }
}
