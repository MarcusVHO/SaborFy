package com.marcus.saborfy.module.query;

import com.marcus.saborfy.module.user.api.internal.contract.UserQueryApi;
import com.marcus.saborfy.module.user.api.internal.dto.UserInternalResponse;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.finder.UserFinder;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService implements UserQueryApi {
    private final UserFinder finder;
    private final UserMapper mapper;

    public UserQueryService(UserFinder finder, UserMapper mapper) {
        this.finder = finder;
        this.mapper = mapper;
    }

    @Override
    public UserInternalResponse findById(Long id) {
        return mapper.entityToUserInternalResponse(finder.findEntityById(id));
    }
}
