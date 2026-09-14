package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.finder.UserFinder;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.module.user.service.impl.UserService;
import com.marcus.saborfy.shared.exception.ForbiddenOperationException;
import com.marcus.saborfy.shared.exception.UserNotFoundException;
import com.marcus.saborfy.shared.security.CurrentUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserChangeEnableUseCaseTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserFinder finder;

    @InjectMocks
    private UserService service;

    @Test
    void shouldEnableUser() {
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);
        User user = mock(User.class);

        when(currentUser.getHighestRole()).thenReturn(RoleName.ADMIN);
        when(currentUser.companyId()).thenReturn(1L);

        when(finder.findEntityByIdOrThrow(userId))
                .thenReturn(user);

        when(user.getRoleName()).thenReturn(RoleName.WAITER);
        when(user.getRestaurantId()).thenReturn(1L);

        service.changeEnableUserUseCase(
                currentUser,
                userId,
                true
        );

        verify(user).enable();
        verify(user, never()).disable();
        verify(repository).save(user);
    }

    @Test
    void shouldDisableUser() {
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);
        User user = mock(User.class);

        when(currentUser.getHighestRole()).thenReturn(RoleName.ADMIN);
        when(currentUser.companyId()).thenReturn(1L);

        when(finder.findEntityByIdOrThrow(userId))
                .thenReturn(user);

        when(user.getRoleName()).thenReturn(RoleName.WAITER);
        when(user.getRestaurantId()).thenReturn(1L);

        service.changeEnableUserUseCase(
                currentUser,
                userId,
                false
        );

        verify(user).disable();
        verify(user, never()).enable();
        verify(repository).save(user);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);

        when(finder.findEntityByIdOrThrow(userId))
                .thenThrow(new UserNotFoundException());

        assertThrows(
                UserNotFoundException.class,
                () -> service.changeEnableUserUseCase(
                        currentUser,
                        userId,
                        true
                )
        );

        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCannotManageUser() {
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);
        User user = mock(User.class);

        when(currentUser.getHighestRole()).thenReturn(RoleName.WAITER);

        when(finder.findEntityByIdOrThrow(userId))
                .thenReturn(user);

        when(user.getRoleName()).thenReturn(RoleName.ADMIN);

        assertThrows(
                ForbiddenOperationException.class,
                () -> service.changeEnableUserUseCase(
                        currentUser,
                        userId,
                        true
                )
        );

        verify(user, never()).enable();
        verify(user, never()).disable();
        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenUserBelongsToAnotherRestaurant() {
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);
        User user = mock(User.class);

        when(currentUser.getHighestRole()).thenReturn(RoleName.ADMIN);
        when(currentUser.companyId()).thenReturn(1L);

        when(finder.findEntityByIdOrThrow(userId))
                .thenReturn(user);

        when(user.getRoleName()).thenReturn(RoleName.WAITER);
        when(user.getRestaurantId()).thenReturn(2L);

        assertThrows(
                ForbiddenOperationException.class,
                () -> service.changeEnableUserUseCase(
                        currentUser,
                        userId,
                        true
                )
        );

        verify(user, never()).enable();
        verify(user, never()).disable();
        verify(repository, never()).save(any());
    }
}