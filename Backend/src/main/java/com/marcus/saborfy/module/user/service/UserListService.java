package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.enuns.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserListService {
    Page<UserResponse> getPageUserUseCase(String searchText, RoleName roleName, Long restaurantId, Pageable pageable);
}
