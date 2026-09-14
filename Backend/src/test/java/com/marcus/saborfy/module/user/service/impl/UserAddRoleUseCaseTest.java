package com.marcus.saborfy.module.user.service.impl;

import com.marcus.saborfy.module.user.dto.request.AddRoleRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.entity.Role;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.RoleRepository;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.shared.exception.RoleNotFoundException;
import com.marcus.saborfy.shared.exception.UserNotFoundException;
import com.marcus.saborfy.shared.security.CurrentUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRoleTest {

    @Mock
    private UserRepository repository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserValidation validation;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserRoleServiceImpl service;

    // ============================================================
    // addRoleUseCase
    // ============================================================

    @Test
    void shouldAddRoleSuccessfully() {

        // Arrange
        Long userId = 1L;
        Long restaurantId = 10L;

        AddRoleRequest request =
                new AddRoleRequest(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                restaurantId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        Role role = new Role();
        role.setName(RoleName.WAITER);

        UserResponse expectedResponse = mock(UserResponse.class);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(restaurantId);

        when(roleRepository.findByName(request.roleName()))
                .thenReturn(Optional.of(role));

        when(mapper.entityToUserResponse(user))
                .thenReturn(expectedResponse);

        // Act
        UserResponse result = service.addRoleUseCase(
                currentUser,
                userId,
                request
        );

        // Assert
        assertNotNull(result);
        assertSame(expectedResponse, result);

        verify(repository)
                .findById(userId);

        verify(validation)
                .validateCanAssignRole(
                        currentUser.getHighestRole(),
                        request.roleName()
                );

        verify(validation)
                .validateRestaurant(
                        currentUser.companyId(),
                        user.getRestaurantId()
                );

        verify(roleRepository)
                .findByName(request.roleName());

        verify(validation)
                .validateCanManage(
                        currentUser.getHighestRole(),
                        user.getRoleName()
                );

        verify(user)
                .addRole(role);

        verify(repository)
                .save(user);

        verify(mapper)
                .entityToUserResponse(user);
    }

    // ============================================================
    // User not found
    // ============================================================

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {

        // Arrange
        Long userId = 1L;

        AddRoleRequest request =
                new AddRoleRequest(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                10L,
                new SimpleGrantedAuthority("ADMIN")
        );

        when(repository.findById(userId))
                .thenReturn(Optional.empty());

        // Act
        UserNotFoundException exception =
                assertThrows(
                        UserNotFoundException.class,
                        () -> service.addRoleUseCase(
                                currentUser,
                                userId,
                                request
                        )
                );

        // Assert
        assertNotNull(exception);

        verify(repository)
                .findById(userId);

        verifyNoInteractions(
                validation,
                roleRepository,
                mapper
        );
    }

    // ============================================================
    // Permission - assign role
    // ============================================================

    @Test
    void shouldValidatePermissionBeforeAssigningRole() {

        // Arrange
        Long userId = 1L;
        Long restaurantId = 10L;

        AddRoleRequest request =
                new AddRoleRequest(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                restaurantId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        Role role = new Role();
        role.setName(RoleName.WAITER);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(restaurantId);

        when(roleRepository.findByName(request.roleName()))
                .thenReturn(Optional.of(role));

        when(mapper.entityToUserResponse(user))
                .thenReturn(mock(UserResponse.class));

        // Act
        service.addRoleUseCase(
                currentUser,
                userId,
                request
        );

        // Assert
        verify(validation)
                .validateCanAssignRole(
                        currentUser.getHighestRole(),
                        request.roleName()
                );
    }

    // ============================================================
    // Restaurant
    // ============================================================

    @Test
    void shouldValidateUserRestaurant() {

        // Arrange
        Long userId = 1L;
        Long restaurantId = 10L;

        AddRoleRequest request =
                new AddRoleRequest(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                restaurantId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        Role role = new Role();
        role.setName(RoleName.WAITER);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(restaurantId);

        when(roleRepository.findByName(request.roleName()))
                .thenReturn(Optional.of(role));

        when(mapper.entityToUserResponse(user))
                .thenReturn(mock(UserResponse.class));

        // Act
        service.addRoleUseCase(
                currentUser,
                userId,
                request
        );

        // Assert
        verify(validation)
                .validateRestaurant(
                        currentUser.companyId(),
                        user.getRestaurantId()
                );
    }

    // ============================================================
    // Role not found
    // ============================================================

    @Test
    void shouldThrowExceptionWhenRoleDoesNotExist() {

        // Arrange
        Long userId = 1L;
        Long restaurantId = 10L;

        AddRoleRequest request =
                new AddRoleRequest(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                restaurantId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(restaurantId);

        when(roleRepository.findByName(request.roleName()))
                .thenReturn(Optional.empty());

        // Act
        RoleNotFoundException exception =
                assertThrows(
                        RoleNotFoundException.class,
                        () -> service.addRoleUseCase(
                                currentUser,
                                userId,
                                request
                        )
                );

        // Assert
        assertNotNull(exception);

        verify(repository)
                .findById(userId);

        verify(validation)
                .validateCanAssignRole(
                        currentUser.getHighestRole(),
                        request.roleName()
                );

        verify(validation)
                .validateRestaurant(
                        currentUser.companyId(),
                        user.getRestaurantId()
                );

        verify(roleRepository)
                .findByName(request.roleName());

        verify(validation, never())
                .validateCanManage(any(), any());

        verify(user, never())
                .addRole(any());

        verify(repository, never())
                .save(any());

        verifyNoInteractions(mapper);
    }

    // ============================================================
    // Permission - manage current role
    // ============================================================

    @Test
    void shouldValidatePermissionToManageCurrentUserRole() {

        // Arrange
        Long userId = 1L;
        Long restaurantId = 10L;

        AddRoleRequest request =
                new AddRoleRequest(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                restaurantId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        Role role = new Role();
        role.setName(RoleName.WAITER);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(restaurantId);

        when(roleRepository.findByName(request.roleName()))
                .thenReturn(Optional.of(role));

        when(mapper.entityToUserResponse(user))
                .thenReturn(mock(UserResponse.class));

        // Act
        service.addRoleUseCase(
                currentUser,
                userId,
                request
        );

        // Assert
        verify(validation)
                .validateCanManage(
                        currentUser.getHighestRole(),
                        user.getRoleName()
                );
    }

    // ============================================================
    // Save
    // ============================================================

    @Test
    void shouldSaveUserAfterAddingRole() {

        // Arrange
        Long userId = 1L;
        Long restaurantId = 10L;

        AddRoleRequest request =
                new AddRoleRequest(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                restaurantId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        Role role = new Role();
        role.setName(RoleName.WAITER);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(restaurantId);

        when(roleRepository.findByName(request.roleName()))
                .thenReturn(Optional.of(role));

        when(mapper.entityToUserResponse(user))
                .thenReturn(mock(UserResponse.class));

        // Act
        service.addRoleUseCase(
                currentUser,
                userId,
                request
        );

        // Assert
        verify(user)
                .addRole(role);

        verify(repository)
                .save(user);
    }

    // ============================================================
    // Mapper
    // ============================================================

    @Test
    void shouldReturnMappedUserResponse() {

        // Arrange
        Long userId = 1L;
        Long restaurantId = 10L;

        AddRoleRequest request =
                new AddRoleRequest(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                restaurantId,
                new SimpleGrantedAuthority("ADMIN")
        );

        User user = mock(User.class);

        Role role = new Role();
        role.setName(RoleName.WAITER);

        UserResponse expectedResponse = mock(UserResponse.class);

        when(repository.findById(userId))
                .thenReturn(Optional.of(user));

        when(user.getRestaurantId())
                .thenReturn(restaurantId);

        when(roleRepository.findByName(request.roleName()))
                .thenReturn(Optional.of(role));

        when(mapper.entityToUserResponse(user))
                .thenReturn(expectedResponse);

        // Act
        UserResponse result = service.addRoleUseCase(
                currentUser,
                userId,
                request
        );

        // Assert
        assertSame(expectedResponse, result);

        verify(mapper)
                .entityToUserResponse(user);
    }
}