package com.marcus.saborfy.module.user.dto.request;

import com.marcus.saborfy.module.user.enuns.RoleName;
import jakarta.validation.constraints.NotNull;

public record AddRoleRequest(
        @NotNull(message = "Role is necessary") RoleName roleName
        ) {
}
