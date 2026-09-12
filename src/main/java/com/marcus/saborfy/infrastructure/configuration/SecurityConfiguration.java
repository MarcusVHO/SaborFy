package com.marcus.saborfy.infrastructure.configuration;

import com.marcus.saborfy.infrastructure.security.SecurityFilter;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private final SecurityFilter securityFilter;

    public SecurityConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .authorizeHttpRequests(
                        authorize -> authorize
                                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                                .requestMatchers(
                                        "/auth/login",
                                        "/auth/refresh",
                                        "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/v3/swagger-docs",
                                        "/v3/api-docs/**"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(
                        securityFilter, UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        String hierarchy = """
                ROLE_SYSTEM_ADMIN > ROLE_OWNER
                ROLE_OWNER > ROLE_ADMIN
                ROLE_ADMIN > ROLE_TELLER
                ROLE_TELLER > ROLE_WAITER
                """;
        return RoleHierarchyImpl.fromHierarchy(hierarchy);
    }



    @Bean
    public AuthorizationManager<RequestAuthorizationContext> authorizationManager(
            RoleHierarchy roleHierarchy
    ) {
        AuthorityAuthorizationManager<RequestAuthorizationContext> manager =
                AuthorityAuthorizationManager.hasRole("ADMIN");

        manager.setRoleHierarchy(roleHierarchy);

        return manager;
    }
}
