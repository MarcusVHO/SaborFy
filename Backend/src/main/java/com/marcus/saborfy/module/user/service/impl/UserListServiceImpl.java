package com.marcus.saborfy.module.user.service.impl;

import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.repository.RoleRepository;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.module.user.service.UserListService;
import com.marcus.saborfy.shared.exception.RoleNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserListServiceImpl implements UserListService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;

    public UserListServiceImpl(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<UserResponse> getPageUserUseCase(String searchText, RoleName roleName, Long restaurantId, Pageable pageable) {
        searchText = searchText == null ? "" : searchText;
        Long roleId = roleName != null
                ? roleRepository.findByName(roleName)
                .orElseThrow(RoleNotFoundException::new)
                .getId()
                : null;
        return repository.findAllUsers(searchText, roleId, pageable, restaurantId);
    }
}
