package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.dto.request.ChangePasswordRequest;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.finder.UserFinder;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.shared.exception.ForbiddenOperationException;
import com.marcus.saborfy.shared.security.CurrentUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserChangePasswordUseCaseTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserFinder finder;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService service;

    @Test
    void shouldChangeOwnPassword() {
        Long userId = 1L;

        CurrentUser currentUser = mock(CurrentUser.class);

        ChangePasswordRequest request = new ChangePasswordRequest(
                userId,
                "old-password",
                "new-password"
        );

        User user = mock(User.class);

        when(currentUser.id()).thenReturn(userId);
        when(currentUser.companyId()).thenReturn(1L);

        when(finder.findEntityByIdOrThrow(userId))
                .thenReturn(user);

        when(user.getRestaurantId()).thenReturn(1L);
        when(user.isActive()).thenReturn(true);
        when(user.getPasswordHash()).thenReturn("old-password-hash");

        when(passwordEncoder.matches(
                "old-password",
                "old-password-hash"
        )).thenReturn(true);

        when(passwordEncoder.encode("new-password"))
                .thenReturn("encoded-new-password");

        service.changePasswordUseCase(currentUser, request);

        verify(finder).findEntityByIdOrThrow(userId);

        verify(passwordEncoder)
                .matches("old-password", "old-password-hash");

        verify(passwordEncoder)
                .encode("new-password");

        verify(user)
                .setPasswordHash("encoded-new-password");

        verify(repository)
                .save(user);
    }

    @Test
    void shouldChangeAnotherUserPasswordWhenCurrentUserCanManage() {
        Long targetUserId = 2L;
        Long adminId = 1L;

        CurrentUser currentUser = mock(CurrentUser.class);

        ChangePasswordRequest request = new ChangePasswordRequest(
                targetUserId,
                "admin-password",
                "new-password"
        );

        User targetUser = mock(User.class);
        User adminUser = mock(User.class);

        when(currentUser.id()).thenReturn(adminId);
        when(currentUser.companyId()).thenReturn(1L);
        when(currentUser.getHighestRole()).thenReturn(RoleName.ADMIN);

        when(finder.findEntityByIdOrThrow(targetUserId))
                .thenReturn(targetUser);

        when(finder.findEntityByIdOrThrow(adminId))
                .thenReturn(adminUser);

        when(targetUser.getRestaurantId()).thenReturn(1L);
        when(targetUser.getRoleName()).thenReturn(RoleName.WAITER);

        when(targetUser.isActive()).thenReturn(true);

        when(adminUser.getPasswordHash())
                .thenReturn("admin-password-hash");

        when(passwordEncoder.matches(
                "admin-password",
                "admin-password-hash"
        )).thenReturn(true);

        when(passwordEncoder.encode("new-password"))
                .thenReturn("encoded-new-password");

        service.changePasswordUseCase(currentUser, request);

        verify(finder).findEntityByIdOrThrow(targetUserId);
        verify(finder).findEntityByIdOrThrow(adminId);

        verify(passwordEncoder)
                .matches("admin-password", "admin-password-hash");

        verify(passwordEncoder)
                .encode("new-password");

        verify(targetUser)
                .setPasswordHash("encoded-new-password");

        verify(repository)
                .save(targetUser);
    }

    @Test
    void shouldThrowExceptionWhenUserBelongsToAnotherRestaurant() {
        Long targetUserId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);

        ChangePasswordRequest request = new ChangePasswordRequest(
                targetUserId,
                "password",
                "new-password"
        );

        User user = mock(User.class);

        when(currentUser.companyId()).thenReturn(1L);

        when(finder.findEntityByIdOrThrow(targetUserId))
                .thenReturn(user);

        when(user.getRestaurantId()).thenReturn(2L);

        assertThrows(
                ForbiddenOperationException.class,
                () -> service.changePasswordUseCase(
                        currentUser,
                        request
                )
        );

        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCannotManageAnotherUser() {
        Long targetUserId = 2L;
        Long currentUserId = 1L;

        CurrentUser currentUser = mock(CurrentUser.class);

        ChangePasswordRequest request = new ChangePasswordRequest(
                targetUserId,
                "password",
                "new-password"
        );

        User targetUser = mock(User.class);

        when(currentUser.id()).thenReturn(currentUserId);
        when(currentUser.companyId()).thenReturn(1L);
        when(currentUser.getHighestRole()).thenReturn(RoleName.WAITER);

        when(finder.findEntityByIdOrThrow(targetUserId))
                .thenReturn(targetUser);

        when(targetUser.getRestaurantId()).thenReturn(1L);
        when(targetUser.getRoleName()).thenReturn(RoleName.ADMIN);

        assertThrows(
                ForbiddenOperationException.class,
                () -> service.changePasswordUseCase(
                        currentUser,
                        request
                )
        );

        verify(finder, times(1))
                .findEntityByIdOrThrow(targetUserId);

        verify(repository, never()).save(any());
    }

    @Test
    void shouldNotFindAdminUserWhenChangingOwnPassword() {
        Long userId = 1L;

        CurrentUser currentUser = mock(CurrentUser.class);

        ChangePasswordRequest request = new ChangePasswordRequest(
                userId,
                "old-password",
                "new-password"
        );

        User user = mock(User.class);

        when(currentUser.id()).thenReturn(userId);
        when(currentUser.companyId()).thenReturn(1L);

        when(finder.findEntityByIdOrThrow(userId))
                .thenReturn(user);

        when(user.getRestaurantId()).thenReturn(1L);
        when(user.isActive()).thenReturn(true);
        when(user.getPasswordHash()).thenReturn("old-password-hash");

        when(passwordEncoder.matches(
                "old-password",
                "old-password-hash"
        )).thenReturn(true);

        when(passwordEncoder.encode("new-password"))
                .thenReturn("encoded-new-password");

        service.changePasswordUseCase(currentUser, request);

        verify(finder, times(1))
                .findEntityByIdOrThrow(userId);

        verify(passwordEncoder)
                .matches("old-password", "old-password-hash");

        verify(user)
                .setPasswordHash("encoded-new-password");

        verify(repository)
                .save(user);
    }
}