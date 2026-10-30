package com.marcus.saborfy.module.user.mapper;

import com.marcus.saborfy.module.user.api.internal.dto.AuthenticationUserDataResponse;
import com.marcus.saborfy.module.user.api.internal.dto.UserInternalResponse;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.entity.Role;
import com.marcus.saborfy.module.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    AuthenticationUserDataResponse entityToAuthenticationUserDataResponse(User user);
    UserResponse entityToUserResponse(User user);
    UserInternalResponse entityToUserInternalResponse(User user);
    default String roleToString(Role role) {
        return role == null ? null : String.valueOf(role.getName());
    }
}
