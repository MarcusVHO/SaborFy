package com.marcus.saborfy.module.user.service.impl;

import com.marcus.saborfy.module.user.dto.request.ChangePasswordRequest;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.finder.UserFinder;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.module.user.service.UserUpdateService;
import com.marcus.saborfy.shared.exception.InvalidCredentialException;
import com.marcus.saborfy.shared.security.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {
    private final UserFinder finder;
    private final PermissionValidator validator;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserUpdateServiceImpl(UserFinder finder, PermissionValidator validator, UserRepository repository, PasswordEncoder passwordEncoder) {
        this.finder = finder;
        this.validator = validator;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void changePasswordUseCase(CurrentUser currentUser, ChangePasswordRequest request) {
        User user = finder.findEntityByIdOrThrow(request.userId());
        validator.validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        if (currentUser.id().equals(request.userId())) {
            changePassword(request.password(), request.newPassword(), user, user);
        } else {
            validator.validateCanManage(currentUser.getHighestRole(), user.getRoleName());
            User userAdmin = finder.findEntityByIdOrThrow(currentUser.id());
            changePassword(request.password(), request.newPassword(), user, userAdmin);
        }
        repository.save(user);
    }

    @Override
    public void changeEnableUserUseCase(CurrentUser currentUser, Long userId, boolean state) {
        User user = finder.findEntityByIdOrThrow(userId);
        validator.validateCanManage(currentUser.getHighestRole(), user.getRoleName());
        validator.validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        if (state) {
            user.enable();
        } else {
            user.disable();
        }
        repository.save(user);
    }

    @Override
    public void changeNameUseCase(CurrentUser currentUser, Long userId, String name) {
        User user = finder.findEntityByIdOrThrow(userId);
        validator.validateCanManage(currentUser.getHighestRole(), user.getRoleName());
        validator.validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        user.update(name);
        repository.save(user);
    }

    private void changePassword(String password, String newPassword, User user, User userRequesting) {
        if (
                !user.isActive()
                        || !passwordEncoder.matches(password, userRequesting.getPasswordHash())
        ) {
            throw new InvalidCredentialException();
        }
        user.setPasswordHash(passwordEncoder.encode(newPassword.trim()));
    }
}
