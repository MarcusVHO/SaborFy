package com.br.marcus.saborfy.infra.security.config;


import lombok.Builder;
import net.bytebuddy.agent.VirtualMachine;

@Builder
public record JWTUserData(String userId, Long registration) {
}
