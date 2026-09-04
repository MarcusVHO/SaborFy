package com.marcus.template.module.user.api.internal.contract;

import com.marcus.template.module.user.api.internal.dto.UserInternalResponse;

public interface UserQueryApi {
    UserInternalResponse findById(Long id);
}
