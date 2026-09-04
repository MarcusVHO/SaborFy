package com.marcus.template.infrastructure.security;

import com.marcus.template.shared.security.CurrentUser;
import com.marcus.template.infrastructure.security.dto.UserPayloadData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
                CurrentUser user = new CurrentUser(
                        userPayloadData.id(),
                        userPayloadData.companyId(),
                        userPayloadData.role()
                );
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        List.of()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request, response);
    }
}
