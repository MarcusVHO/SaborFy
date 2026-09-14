package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.dto.request.ChangePasswordRequest;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.finder.UserFinder;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.shared.exception.InvalidCredentialException;
import com.marcus.saborfy.shared.security.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {
    private final UserFinder finder;
    private final PermissionValidator validator;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserUpdateService(UserFinder finder, PermissionValidator validator, UserRepository repository, PasswordEncoder passwordEncoder) {
        this.finder = finder;
        this.validator = validator;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    public void updatePassword(CurrentUser currentUser, ChangePasswordRequest request) {
        User user = finder.findEntityByIdOrThrow(request.userId());
        validator.validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        if (currentUser.id().equals(request.userId())) {
            updatePassword(
                    request.password(),
                    request.newPassword(),
                    user,
                    user.getPassword());
        } else {
            validator.validateCanManage(currentUser.getHighestRole(), user.getRoleName());
            updatePassword(
                    request.password(),
                    request.newPassword(),
                    user,
                    repository.findPasswordById(currentUser.id())
            );
        }
        repository.save(user);
    }

    public void changeEnableUser(CurrentUser currentUser, Long userId, boolean state) {
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

    public void changeName(CurrentUser currentUser, Long userId, String name) {
        User user = finder.findEntityByIdOrThrow(userId);
        validator.validateCanManage(currentUser.getHighestRole(), user.getRoleName());
        validator.validateRestaurant(currentUser.companyId(), user.getRestaurantId());
        user.update(name);
        repository.save(user);
    }

    private void updatePassword(String password, String newPassword, User user, String passwordHash) {
        if (
                !user.isActive()
                        || !passwordEncoder.matches(password, passwordHash)
        ) {
            throw new InvalidCredentialException();
        }
        user.setPasswordHash(passwordEncoder.encode(newPassword.trim()));
    }
}
