package com.br.marcus.saborfy.domain.user.service;

import com.br.marcus.saborfy.domain.user.dto.request.RegisterUserRequest;
import com.br.marcus.saborfy.domain.user.entity.User;

public interface UserService {
    User create(RegisterUserRequest request);

}
