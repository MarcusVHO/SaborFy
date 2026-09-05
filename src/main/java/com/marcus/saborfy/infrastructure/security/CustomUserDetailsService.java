package com.marcus.saborfy.infrastructure.security;

import com.marcus.saborfy.infrastructure.security.dto.UserData;
import com.marcus.saborfy.module.user.api.internal.contract.UserAuthenticationQuery;
import com.marcus.saborfy.module.user.api.internal.dto.AuthenticationUserDataResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAuthenticationQuery userAuthenticationQuery;

    public CustomUserDetailsService(UserAuthenticationQuery userAuthenticationQuery) {
        this.userAuthenticationQuery = userAuthenticationQuery;
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username) {
        AuthenticationUserDataResponse user =
                userAuthenticationQuery.findByUsername(username);

        return new UserData(
                user.id(),
                user.companyId(),
                user.username(),
                user.passwordHash(),
                user.role(),
                user.active()
        );
    }
}
