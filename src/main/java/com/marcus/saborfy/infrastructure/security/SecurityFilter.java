package com.marcus.saborfy.infrastructure.security;

import com.marcus.saborfy.shared.security.CurrentUser;
import com.marcus.saborfy.infrastructure.security.dto.UserPayloadData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenConfig tokenConfig;

    public SecurityFilter(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    protected  void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
        ) throws IOException, ServletException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length());
            Optional<UserPayloadData> optUser = tokenConfig.validateToken(token);

            if (optUser.isPresent()) {
                UserPayloadData userPayloadData = optUser.get();
                UsernamePasswordAuthenticationToken authentication = getUsernamePasswordAuthenticationToken(userPayloadData);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request, response);
    }

    private static @NonNull UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(UserPayloadData userPayloadData) {

        CurrentUser user = new CurrentUser(
                userPayloadData.id(),
                userPayloadData.companyId(),
                userPayloadData.role()
        );
        System.out.println(userPayloadData.role());
        return new UsernamePasswordAuthenticationToken(
                user,
                null,
                userPayloadData.role()
        );
    }
}
