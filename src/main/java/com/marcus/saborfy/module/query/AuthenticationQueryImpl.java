package com.marcus.saborfy.module.query;

import com.marcus.saborfy.shared.exception.UserNotFoundException;
import com.marcus.saborfy.module.user.api.internal.dto.AuthenticationUserDataResponse;
import com.marcus.saborfy.module.user.api.internal.contract.UserAuthenticationQuery;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationQueryImpl implements UserAuthenticationQuery {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public AuthenticationUserDataResponse findByUsername(String username) {
        return userRepository.findByRegistration(username)
                .map(mapper::entityToAuthenticationUserDataResponse).orElseThrow(UserNotFoundException::new);
    }
}
