package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.dto.request.RegisterUserRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.shared.security.CurrentUser;

public interface UserRegisterService {
    UserResponse registerUseCase(CurrentUser currentUser, RegisterUserRequest request);
}
