package com.marcus.saborfy.module.user.dto.response;

import java.time.Instant;
import java.util.List;

public record UserResponse(
    Long id,
    Long restaurantId,
    String username,
    List<String> role,
    boolean active,
    Instant createdAt,
    Instant updatedAt
) {
}
