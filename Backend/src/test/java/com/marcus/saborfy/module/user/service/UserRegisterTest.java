package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.dto.request.RegisterUserRequest;
import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.entity.Role;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.RoleRepository;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.shared.exception.RoleNotFoundException;
import com.marcus.saborfy.shared.exception.UserAlreadyExistsException;
import com.marcus.saborfy.shared.security.CurrentUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRegisterTest {

    @Mock
    private UserRepository repository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper mapper;

    @Mock
    private PermissionValidator validator;

    @InjectMocks
    private UserRegisterService service;

    // ============================================================
    // registerUseCase
    // ============================================================

    @Test
    void shouldRegisterUserSuccessfully() {

        // Arrange
        Long companyId = 1L;

        RegisterUserRequest request = new RegisterUserRequest(
                "12345",
                "123456",
                "Marcus",
                RoleName.WAITER
        );

        Role role = new Role();
        role.setName(RoleName.WAITER);

        User savedUser = mock(User.class);
        UserResponse expectedResponse = mock(UserResponse.class);

        CurrentUser currentUser = new CurrentUser(
                1L,
                companyId,
                new SimpleGrantedAuthority("ADMIN")
        );

        when(repository.existsByRegistration(request.registration()))
                .thenReturn(false);

        when(roleRepository.findByName(request.role()))
                .thenReturn(Optional.of(role));

        when(passwordEncoder.encode(request.password().trim()))
                .thenReturn("encoded-password");

        when(repository.save(any(User.class)))
                .thenReturn(savedUser);

        when(mapper.entityToUserResponse(savedUser))
                .thenReturn(expectedResponse);

        // Act
        UserResponse result = service.register(
                currentUser,
                request
        );

        // Assert
        assertNotNull(result);
        assertSame(expectedResponse, result);

        verify(repository)
                .existsByRegistration(request.registration());

        verify(validator)
                .validateCanManage(
                        currentUser.getHighestRole(),
                        request.role()
                );

        verify(roleRepository)
                .findByName(request.role());

        verify(passwordEncoder)
                .encode(request.password().trim());

        verify(repository)
                .save(any(User.class));

        verify(mapper)
                .entityToUserResponse(savedUser);
    }

    @Test
    void shouldSaveUserWithCorrectData() {

        // Arrange
        Long companyId = 10L;

        RegisterUserRequest request = new RegisterUserRequest(
                "ABC123",
                "password123",
                "Marcus",
                RoleName.WAITER
        );

        Role role = new Role();
        role.setName(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                companyId,
                new SimpleGrantedAuthority("ADMIN")
        );

        when(repository.existsByRegistration(request.registration()))
                .thenReturn(false);

        when(roleRepository.findByName(request.role()))
                .thenReturn(Optional.of(role));

        when(passwordEncoder.encode(request.password().trim()))
                .thenReturn("encoded-password");

        when(repository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(mapper.entityToUserResponse(any(User.class)))
                .thenReturn(mock(UserResponse.class));

        // Act
        service.register(
                currentUser,
                request
        );

        // Assert
        ArgumentCaptor<User> userCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(repository).save(userCaptor.capture());

        User user = userCaptor.getValue();

        assertEquals(companyId, user.getRestaurantId());
        assertEquals(request.registration(), user.getRegistration());
        assertEquals("encoded-password", user.getPassword());
        assertEquals(request.name(), user.getName());
        assertEquals(role, user.getRole());
    }

    @Test
    void shouldEncodePasswordBeforeSavingUser() {

        // Arrange
        RegisterUserRequest request = new RegisterUserRequest(
                "12345",
                "Marcus",
                "123456",
                RoleName.WAITER
        );

        Role role = new Role();
        role.setName(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                1L,
                new SimpleGrantedAuthority("ADMIN")
        );

        when(repository.existsByRegistration(request.registration()))
                .thenReturn(false);

        when(roleRepository.findByName(request.role()))
                .thenReturn(Optional.of(role));

        when(passwordEncoder.encode("123456"))
                .thenReturn("encoded-password");

        when(repository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(mapper.entityToUserResponse(any(User.class)))
                .thenReturn(mock(UserResponse.class));

        // Act
        service.register(
                currentUser,
                request
        );

        // Assert
        verify(passwordEncoder)
                .encode("123456");

        ArgumentCaptor<User> captor =
                ArgumentCaptor.forClass(User.class);

        verify(repository)
                .save(captor.capture());

        assertEquals(
                "encoded-password",
                captor.getValue().getPassword()
        );
    }

    @Test
    void shouldTrimPasswordBeforeEncoding() {

        // Arrange
        RegisterUserRequest request = new RegisterUserRequest(
                "12345",
                "Marcus",
                "  123456  ",
                RoleName.WAITER
        );

        Role role = new Role();
        role.setName(RoleName.WAITER);

        CurrentUser currentUser = new CurrentUser(
                1L,
                1L,
                new SimpleGrantedAuthority("ADMIN")
        );

        when(repository.existsByRegistration(request.registration()))
                .thenReturn(false);

        when(roleRepository.findByName(request.role()))
                .thenReturn(Optional.of(role));

        when(passwordEncoder.encode("123456"))
                .thenReturn("encoded-password");

        when(repository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(mapper.entityToUserResponse(any(User.class)))
                .thenReturn(mock(UserResponse.class));

        // Act
        service.register(
                currentUser,
                request
        );

        // Assert
        verify(passwordEncoder)
                .encode("123456");
    }

    // ============================================================
    // Registration already exists
    // ============================================================

    @Test
    void shouldThrowExceptionWhenRegistrationAlreadyExists() {

        // Arrange
        RegisterUserRequest request = new RegisterUserRequest(
                "12345",
                "123456",
                "Marcus",
                RoleName.WAITER
        );

        CurrentUser currentUser = new CurrentUser(
                1L,
                1L,
                new SimpleGrantedAuthority("ADMIN")
        );

        when(repository.existsByRegistration(request.registration()))
                .thenReturn(true);

        // Act
        UserAlreadyExistsException exception =
                assertThrows(
                        UserAlreadyExistsException.class,
                        () -> service.register(
                                currentUser,
                                request
                        )
                );

        // Assert
        assertNotNull(exception);

        verify(repository)
                .existsByRegistration(request.registration());



        verify(repository, never())
                .save(any(User.class));
    }

    // ============================================================
    // Permission
    // ============================================================

    @Test
    void shouldValidatePermissionBeforeRegisteringUser() {

        // Arrange
        RegisterUserRequest request = new RegisterUserRequest(
                "12345",
                "123456",
                "Marcus",
                RoleName.WAITER
        );

        CurrentUser currentUser = new CurrentUser(
                1L,
                1L,
                new SimpleGrantedAuthority("ADMIN")
        );

        Role role = new Role();
        role.setName(RoleName.WAITER);

        when(repository.existsByRegistration(request.registration()))
                .thenReturn(false);

        when(roleRepository.findByName(request.role()))
                .thenReturn(Optional.of(role));

        when(passwordEncoder.encode(request.password().trim()))
                .thenReturn("encoded-password");

        when(repository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(mapper.entityToUserResponse(any(User.class)))
                .thenReturn(mock(UserResponse.class));

        // Act
        service.register(
                currentUser,
                request
        );

        // Assert
        verify(validator)
                .validateCanManage(
                        currentUser.getHighestRole(),
                        request.role()
                );
    }

    // ============================================================
    // Role not found
    // ============================================================

    @Test
    void shouldThrowExceptionWhenRoleDoesNotExist() {

        // Arrange
        RegisterUserRequest request = new RegisterUserRequest(
                "12345",
                "123456",
                "Marcus",
                RoleName.WAITER
        );

        CurrentUser currentUser = new CurrentUser(
                1L,
                1L,
                new SimpleGrantedAuthority("ADMIN")
        );

        when(repository.existsByRegistration(request.registration()))
                .thenReturn(false);

        when(roleRepository.findByName(request.role()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(request.password().trim()))
                .thenReturn("encoded-password");

        // Act
        RoleNotFoundException exception =
                assertThrows(
                        RoleNotFoundException.class,
                        () -> service.register(
                                currentUser,
                                request
                        )
                );

        // Assert
        assertNotNull(exception);

        verify(repository)
                .existsByRegistration(request.registration());

        verify(validator)
                .validateCanManage(
                        currentUser.getHighestRole(),
                        request.role()
                );

        verify(roleRepository)
                .findByName(request.role());

        verify(passwordEncoder)
                .encode(request.password().trim());

        verify(repository, never())
                .save(any());

        verifyNoInteractions(mapper);
    }
}