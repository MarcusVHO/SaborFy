package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.dto.request.AddRoleRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.shared.security.CurrentUser;

public interface UserRoleService {
    UserResponse addRoleUseCase(CurrentUser currentUser, Long userId, AddRoleRequest request);
}
