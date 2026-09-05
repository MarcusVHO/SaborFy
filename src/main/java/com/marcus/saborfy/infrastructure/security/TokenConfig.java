package com.marcus.saborfy.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.marcus.saborfy.infrastructure.security.dto.UserPayloadData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Component
public class TokenConfig {
    public Long validate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decode = JWT.require(algorithm)
                    .build()
                    .verify(token);
            return decode.getClaim("id").asLong();
        }
        catch (JWTVerificationException ex) {
            return null;
        }
    }

    @Value("${app.jwt.secret}")
    private String secret;

    public String generateToken(Long userId, Long companyId, List<SimpleGrantedAuthority> roles) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            List<String> roleNames = roles.stream()
                    .map(SimpleGrantedAuthority::getAuthority)
                    .toList();


            return JWT.create()
                    .withClaim("id", userId)
                    .withClaim("companyId", companyId)
                    .withClaim("role", roleNames)
                    .withExpiresAt(genExpirationDate(1))
                    .withIssuedAt(Instant.now())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw  new RuntimeException("Error while generation token", exception);
        }
    }


    public String generateRefreshToken(Long userId, Long companyId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withClaim("id", userId)
                    .withClaim("companyId", companyId)
                    .withExpiresAt(genExpirationDate(5))
                    .withIssuedAt(Instant.now())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw  new RuntimeException("Error while generation token", exception);
        }
    }


    public Optional<UserPayloadData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decode = JWT.require(algorithm)
                    .build()
                    .verify(token);

            List<String> role = decode.getClaim("role").asList(String.class);
            List<SimpleGrantedAuthority> authorities = role.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            return Optional.of(UserPayloadData.builder()
                    .id(decode.getClaim("id").asLong())
                    .companyId(decode.getClaim("companyId").asLong())
                    .role(authorities)
                    .build()
            );
        }
        catch (JWTVerificationException ex) {
            return Optional.empty();
        }

    }

    private Instant genExpirationDate(Integer hours) {
        return LocalDateTime.now()
                .plusHours(hours)
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toInstant();
    }
}
