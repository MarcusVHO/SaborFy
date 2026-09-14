package com.marcus.saborfy.module.user.service.impl;

import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.shared.exception.ForbiddenOperationException;
import org.springframework.stereotype.Component;

@Component
public class PermissionValidator {
    protected void validateCanManage(
            RoleName currentUserRole,
            RoleName newRole
    ) {
        if (currentUserRole.canManager(newRole)) {
            throw new ForbiddenOperationException();
        }
    }

    protected void validateCanAssignRole(
            RoleName currentUserRole,
            RoleName newRole
    ) {
        if (currentUserRole.canManager(newRole)) {
            throw new ForbiddenOperationException();
        }
    }

    protected void validateRestaurant(Long currentRestaurantId, Long newRestaurantId) {
        if (!currentRestaurantId.equals(newRestaurantId)) {
            throw new ForbiddenOperationException();
        }
    }
}
