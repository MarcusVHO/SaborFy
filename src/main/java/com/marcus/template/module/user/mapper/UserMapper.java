package com.marcus.template.module.user.mapper;

import com.marcus.template.module.user.api.internal.dto.AuthenticationUserDataResponse;
import com.marcus.template.module.user.api.internal.dto.UserInternalResponse;
import com.marcus.template.module.user.dto.response.UserResponse;
import com.marcus.template.module.user.entity.Role;
import com.marcus.template.module.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    AuthenticationUserDataResponse entityToAuthenticationUserDataResponse(User user);
    UserResponse entityToUserResponse(User user);
    UserInternalResponse entityToUserInternalResponse(User user);


    default String roleToString(Role role) {
        return role == null ? null : role.getAuthority();
    }
}
