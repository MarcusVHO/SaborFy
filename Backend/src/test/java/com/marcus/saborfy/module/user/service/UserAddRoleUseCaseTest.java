package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.dto.request.AddRoleRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.entity.Role;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.RoleRepository;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.module.user.service.impl.UserService;
import com.marcus.saborfy.shared.exception.ForbiddenOperationException;
import com.marcus.saborfy.shared.exception.RoleNotFoundException;
import com.marcus.saborfy.shared.exception.UserNotFoundException;
import com.marcus.saborfy.shared.security.CurrentUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAddRoleUseCaseTest {

    @Mock
    private UserRepository repository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;


    @Test
    void shouldAddRoleToUserSuccessfully() {

        // Arrange
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);

        AddRoleRequest request = new AddRoleRequest(
                RoleName.WAITER
        );

        User user = mock(User.class);

        Role role = new Role();
        role.setName(RoleName.WAITER);

        UserResponse response = mock(UserResponse.class);

        when(currentUser.getHighestRole())
                .thenReturn(RoleName.ADMIN);

        when(currentUser.companyId())
                .thenReturn(1L);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(1L);

        when(user.getRoleName())
                .thenReturn(RoleName.TELLER);

        when(roleRepository.findByName(RoleName.WAITER))
                .thenReturn(Optional.of(role));

        when(mapper.entityToUserResponse(user))
                .thenReturn(response);

        // Act
        UserResponse result = service.addRoleUseCase(
                currentUser,
                userId,
                request
        );

        // Assert
        verify(user).addRole(role);
        verify(repository).save(user);
        verify(mapper).entityToUserResponse(user);

        assertEquals(response, result);
    }


    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        // Arrange
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);

        AddRoleRequest request = new AddRoleRequest(
                RoleName.WAITER
        );

        when(repository.findById(userId))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(
                UserNotFoundException.class,
                () -> service.addRoleUseCase(
                        currentUser,
                        userId,
                        request
                )
        );

        verify(repository, never()).save(any());
    }


    @Test
    void shouldThrowExceptionWhenCannotAssignRole() {

        // Arrange
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);

        AddRoleRequest request = new AddRoleRequest(
                RoleName.ADMIN
        );

        User user = mock(User.class);

        when(currentUser.getHighestRole())
                .thenReturn(RoleName.TELLER);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        // Act + Assert
        assertThrows(
                ForbiddenOperationException.class,
                () -> service.addRoleUseCase(
                        currentUser,
                        userId,
                        request
                )
        );

        verify(roleRepository, never())
                .findByName(any());

        verify(repository, never())
                .save(any());
    }


    @Test
    void shouldThrowExceptionWhenUserBelongsToAnotherRestaurant() {

        // Arrange
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);

        AddRoleRequest request = new AddRoleRequest(
                RoleName.WAITER
        );

        User user = mock(User.class);

        when(currentUser.getHighestRole())
                .thenReturn(RoleName.ADMIN);

        when(currentUser.companyId())
                .thenReturn(1L);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(2L);

        // Act + Assert
        assertThrows(
                ForbiddenOperationException.class,
                () -> service.addRoleUseCase(
                        currentUser,
                        userId,
                        request
                )
        );

        verify(roleRepository, never())
                .findByName(any());

        verify(repository, never())
                .save(any());
    }


    @Test
    void shouldThrowExceptionWhenRoleNotFound() {

        // Arrange
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);

        AddRoleRequest request = new AddRoleRequest(
                RoleName.WAITER
        );

        User user = mock(User.class);

        when(currentUser.getHighestRole())
                .thenReturn(RoleName.ADMIN);

        when(currentUser.companyId())
                .thenReturn(1L);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(1L);

        when(roleRepository.findByName(RoleName.WAITER))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(
                RoleNotFoundException.class,
                () -> service.addRoleUseCase(
                        currentUser,
                        userId,
                        request
                )
        );

        verify(user, never()).addRole(any());
        verify(repository, never()).save(any());
    }


    @Test
    void shouldThrowExceptionWhenCannotManageUserRole() {

        // Arrange
        Long userId = 2L;

        CurrentUser currentUser = mock(CurrentUser.class);

        AddRoleRequest request = new AddRoleRequest(
                RoleName.WAITER
        );

        User user = mock(User.class);

        Role role = new Role();
        role.setName(RoleName.WAITER);

        when(currentUser.getHighestRole())
                .thenReturn(RoleName.TELLER);

        when(currentUser.companyId())
                .thenReturn(1L);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(1L);

        when(user.getRoleName())
                .thenReturn(RoleName.ADMIN);

        when(roleRepository.findByName(RoleName.WAITER))
                .thenReturn(Optional.of(role));

        // Act + Assert
        assertThrows(
                ForbiddenOperationException.class,
                () -> service.addRoleUseCase(
                        currentUser,
                        userId,
                        request
                )
        );

        verify(user, never()).addRole(any());
        verify(repository, never()).save(any());
    }
}