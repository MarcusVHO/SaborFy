package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.dto.request.ChangePasswordRequest;
import com.marcus.saborfy.shared.security.CurrentUser;

public interface UserUpdateService {
    void changePasswordUseCase(CurrentUser currentUser, ChangePasswordRequest request);
    void changeEnableUserUseCase(CurrentUser currentUser, Long userId, boolean state);
    void changeNameUseCase(CurrentUser currentUser, Long userId, String name);
}
