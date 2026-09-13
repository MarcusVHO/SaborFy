package com.marcus.saborfy.module.user.dto.response;

import com.marcus.saborfy.module.user.enuns.RoleName;

import java.time.Instant;

public record UserResponse(
    Long id,
    Long restaurantId,
    String registration,
    String name,
    RoleName role,
    boolean active,
    Instant createdAt,
    Instant updatedAt
) {
}
