package com.marcus.template.module.user.api.internal.contract;

import com.marcus.template.module.user.api.internal.dto.AuthenticationUserDataResponse;

public interface UserAuthenticationQuery {
   AuthenticationUserDataResponse findByUsername(String username);
}

