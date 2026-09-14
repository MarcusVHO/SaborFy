package com.marcus.saborfy.module.user.service.impl;

import com.marcus.saborfy.module.user.dto.request.ChangePasswordRequest;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.finder.UserFinder;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.shared.exception.InvalidCredentialException;
import com.marcus.saborfy.shared.security.CurrentUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUpdateTest {

    @Mock
    private UserFinder finder;

    @Mock
    private PermissionValidator validator;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserUpdateServiceImpl service;

    @Test
    void shouldChangeOwnPasswordSuccessfully() {

        Long userId = 1L;
        Long companyId = 10L;

        ChangePasswordRequest request = new ChangePasswordRequest(
                userId,
                "oldPassword",
                "newPassword"
        );

        CurrentUser currentUser = new CurrentUser(
                userId,
                companyId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        when(finder.findEntityByIdOrThrow(userId))
                .thenReturn(user);

        when(user.getRestaurantId())
                .thenReturn(companyId);

        when(user.isActive())
                .thenReturn(true);

        when(user.getPasswordHash())
                .thenReturn("encoded-old-password");

        when(passwordEncoder.matches(
                request.password(),
                user.getPasswordHash()
        )).thenReturn(true);

        when(passwordEncoder.encode(
                request.newPassword().trim()
        )).thenReturn("encoded-new-password");

        service.changePasswordUseCase(
                currentUser,
                request
        );

        verify(finder)
                .findEntityByIdOrThrow(userId);

        verify(validator)
                .validateRestaurant(
                        companyId,
                        companyId
                );

        verify(passwordEncoder)
                .matches(
                        request.password(),
                        "encoded-old-password"
                );

        verify(passwordEncoder)
                .encode("newPassword");

        verify(user)
                .setPasswordHash("encoded-new-password");

        verify(repository)
                .save(user);

        verify(validator, never())
                .validateCanManage(any(), any());
    }

    @Test
    void shouldChangeAnotherUserPasswordSuccessfully() {

        Long currentUserId = 1L;
        Long targetUserId = 2L;
        Long companyId = 10L;

        ChangePasswordRequest request = new ChangePasswordRequest(
                targetUserId,
                "adminPassword",
                "newPassword"
        );

        CurrentUser currentUser = new CurrentUser(
                currentUserId,
                companyId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User targetUser = mock(User.class);
        User requestingUser = mock(User.class);

        when(finder.findEntityByIdOrThrow(targetUserId))
                .thenReturn(targetUser);

        when(finder.findEntityByIdOrThrow(currentUserId))
                .thenReturn(requestingUser);

        when(targetUser.getRestaurantId())
                .thenReturn(companyId);

        when(targetUser.getRoleName())
                .thenReturn(RoleName.WAITER);

        when(targetUser.isActive())
                .thenReturn(true);

        when(requestingUser.getPasswordHash())
                .thenReturn("encoded-admin-password");

        when(passwordEncoder.matches(
                request.password(),
                "encoded-admin-password"
        )).thenReturn(true);

        when(passwordEncoder.encode(
                request.newPassword().trim()
        )).thenReturn("encoded-new-password");

        service.changePasswordUseCase(
                currentUser,
                request
        );

        verify(finder)
                .findEntityByIdOrThrow(targetUserId);

        verify(validator)
                .validateRestaurant(
                        companyId,
                        companyId
                );

        verify(validator)
                .validateCanManage(
                        currentUser.getHighestRole(),
                        RoleName.WAITER
                );

        verify(finder)
                .findEntityByIdOrThrow(currentUserId);

        verify(passwordEncoder)
                .matches(
                        "adminPassword",
                        "encoded-admin-password"
                );

        verify(passwordEncoder)
                .encode("newPassword");

        verify(targetUser)
                .setPasswordHash("encoded-new-password");

        verify(repository)
                .save(targetUser);
    }

    @Test
    void shouldThrowExceptionWhenOwnUserIsInactive() {
        CurrentUser currentUser = mock(CurrentUser.class);
        User user = mock(User.class);

        when(currentUser.id()).thenReturn(1L);
        when(currentUser.companyId()).thenReturn(10L);
        when(finder.findEntityByIdOrThrow(1L)).thenReturn(user);
        when(user.getRestaurantId()).thenReturn(10L);
        when(user.isActive()).thenReturn(false);

        ChangePasswordRequest request =
                new ChangePasswordRequest(1L, "oldPassword", "newPassword");

        assertThrows(
                InvalidCredentialException.class,
                () -> service.changePasswordUseCase(currentUser, request)
        );

        verify(passwordEncoder, never())
                .matches(anyString(), anyString());

        verify(passwordEncoder, never())
                .encode(anyString());

        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsInvalid() {

        Long userId = 1L;
        Long companyId = 10L;

        ChangePasswordRequest request = new ChangePasswordRequest(
                userId,
                "wrongPassword",
                "newPassword"
        );

        CurrentUser currentUser = new CurrentUser(
                userId,
                companyId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        when(finder.findEntityByIdOrThrow(userId))
                .thenReturn(user);

        when(user.getRestaurantId())
                .thenReturn(companyId);

        when(user.isActive())
                .thenReturn(true);

        when(user.getPasswordHash())
                .thenReturn("encoded-password");

        when(passwordEncoder.matches(
                "wrongPassword",
                "encoded-password"
        )).thenReturn(false);

        assertThrows(
                InvalidCredentialException.class,
                () -> service.changePasswordUseCase(
                        currentUser,
                        request
                )
        );

        verify(passwordEncoder)
                .matches(
                        "wrongPassword",
                        "encoded-password"
                );

        verify(passwordEncoder, never())
                .encode(any());

        verify(user, never())
                .setPasswordHash(any());

        verify(repository, never())
                .save(any());
    }

    @Test
    void shouldTrimNewPasswordBeforeEncoding() {

        Long userId = 1L;
        Long companyId = 10L;

        ChangePasswordRequest request = new ChangePasswordRequest(
                userId,
                "oldPassword",
                "  newPassword  "
        );

        CurrentUser currentUser = new CurrentUser(
                userId,
                companyId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        when(finder.findEntityByIdOrThrow(userId))
                .thenReturn(user);

        when(user.getRestaurantId())
                .thenReturn(companyId);

        when(user.isActive())
                .thenReturn(true);

        when(user.getPasswordHash())
                .thenReturn("encoded-password");

        when(passwordEncoder.matches(
                "oldPassword",
                "encoded-password"
        )).thenReturn(true);

        when(passwordEncoder.encode("newPassword"))
                .thenReturn("encoded-new-password");

        service.changePasswordUseCase(
                currentUser,
                request
        );

        verify(passwordEncoder)
                .encode("newPassword");

        verify(user)
                .setPasswordHash("encoded-new-password");

        verify(repository)
                .save(user);
    }

    @Test
    void shouldEnableUserSuccessfully() {

        Long currentUserId = 1L;
        Long targetUserId = 2L;
        Long companyId = 10L;

        CurrentUser currentUser = new CurrentUser(
                currentUserId,
                companyId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        when(finder.findEntityByIdOrThrow(targetUserId))
                .thenReturn(user);

        when(user.getRestaurantId())
                .thenReturn(companyId);

        when(user.getRoleName())
                .thenReturn(RoleName.WAITER);

        service.changeEnableUserUseCase(
                currentUser,
                targetUserId,
                true
        );

        verify(finder)
                .findEntityByIdOrThrow(targetUserId);

        verify(validator)
                .validateCanManage(
                        currentUser.getHighestRole(),
                        RoleName.WAITER
                );

        verify(validator)
                .validateRestaurant(
                        companyId,
                        companyId
                );

        verify(user)
                .enable();

        verify(user, never())
                .disable();

        verify(repository)
                .save(user);
    }

    @Test
    void shouldDisableUserSuccessfully() {

        Long currentUserId = 1L;
        Long targetUserId = 2L;
        Long companyId = 10L;

        CurrentUser currentUser = new CurrentUser(
                currentUserId,
                companyId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        when(finder.findEntityByIdOrThrow(targetUserId))
                .thenReturn(user);

        when(user.getRestaurantId())
                .thenReturn(companyId);

        when(user.getRoleName())
                .thenReturn(RoleName.WAITER);

        service.changeEnableUserUseCase(
                currentUser,
                targetUserId,
                false
        );

        verify(validator)
                .validateCanManage(
                        currentUser.getHighestRole(),
                        RoleName.WAITER
                );

        verify(validator)
                .validateRestaurant(
                        companyId,
                        companyId
                );

        verify(user)
                .disable();

        verify(user, never())
                .enable();

        verify(repository)
                .save(user);
    }

    @Test
    void shouldChangeUserNameSuccessfully() {

        Long currentUserId = 1L;
        Long targetUserId = 2L;
        Long companyId = 10L;

        String newName = "Marcus Silva";

        CurrentUser currentUser = new CurrentUser(
                currentUserId,
                companyId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        when(finder.findEntityByIdOrThrow(targetUserId))
                .thenReturn(user);

        when(user.getRestaurantId())
                .thenReturn(companyId);

        when(user.getRoleName())
                .thenReturn(RoleName.WAITER);

        service.changeNameUseCase(
                currentUser,
                targetUserId,
                newName
        );

        verify(finder)
                .findEntityByIdOrThrow(targetUserId);

        verify(validator)
                .validateCanManage(
                        currentUser.getHighestRole(),
                        RoleName.WAITER
                );

        verify(validator)
                .validateRestaurant(
                        companyId,
                        companyId
                );

        verify(user)
                .update(newName);

        verify(repository)
                .save(user);
    }
}