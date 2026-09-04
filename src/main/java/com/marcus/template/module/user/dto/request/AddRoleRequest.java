package com.marcus.template.module.user.dto.request;

import com.marcus.template.module.user.enuns.RoleName;
import jakarta.validation.constraints.NotNull;

public record AddRoleRequest(
        @NotNull(message = "Role is necessary") RoleName roleName
        ) {
}
