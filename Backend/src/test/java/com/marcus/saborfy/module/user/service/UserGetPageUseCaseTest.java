package com.marcus.saborfy.module.user.service;

import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.enuns.RoleName;
import com.marcus.saborfy.module.user.entity.Role;
import com.marcus.saborfy.module.user.mapper.UserMapper;
import com.marcus.saborfy.module.user.repository.RoleRepository;
import com.marcus.saborfy.module.user.repository.UserRepository;
import com.marcus.saborfy.module.user.service.impl.UserService;
import com.marcus.saborfy.shared.exception.RoleNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserGetPageUseCaseTest {

    @Mock
    private UserRepository repository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;

    @Test
    void shouldReturnUsersPageWithSearchAndRole() {
        String searchText = "Marcus";
        RoleName roleName = RoleName.WAITER;
        Long restaurantId = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        Role role = new Role();
        role.setId(2L);
        role.setName(roleName);

        UserResponse response = mock(UserResponse.class);
        Page<UserResponse> expectedPage =
                new PageImpl<>(List.of(response));

        when(roleRepository.findByName(roleName))
                .thenReturn(Optional.of(role));

        when(repository.findAllUsers(
                searchText,
                role.getId(),
                pageable,
                restaurantId
        )).thenReturn(expectedPage);

        Page<UserResponse> result = service.getPageUserUseCase(
                searchText,
                roleName,
                restaurantId,
                pageable
        );

        assertEquals(expectedPage, result);

        verify(roleRepository).findByName(roleName);
        verify(repository).findAllUsers(
                searchText,
                role.getId(),
                pageable,
                restaurantId
        );
    }

    @Test
    void shouldUseEmptySearchTextWhenSearchTextIsNull() {
        RoleName roleName = RoleName.WAITER;
        Long restaurantId = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        Role role = new Role();
        role.setId(2L);
        role.setName(roleName);

        Page<UserResponse> expectedPage = Page.empty();

        when(roleRepository.findByName(roleName))
                .thenReturn(Optional.of(role));

        when(repository.findAllUsers(
                "",
                role.getId(),
                pageable,
                restaurantId
        )).thenReturn(expectedPage);

        Page<UserResponse> result = service.getPageUserUseCase(
                null,
                roleName,
                restaurantId,
                pageable
        );

        assertEquals(expectedPage, result);

        verify(repository).findAllUsers(
                "",
                role.getId(),
                pageable,
                restaurantId
        );
    }

    @Test
    void shouldUseNullRoleIdWhenRoleNameIsNull() {
        String searchText = "Marcus";
        Long restaurantId = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        Page<UserResponse> expectedPage = Page.empty();

        when(repository.findAllUsers(
                searchText,
                null,
                pageable,
                restaurantId
        )).thenReturn(expectedPage);

        Page<UserResponse> result = service.getPageUserUseCase(
                searchText,
                null,
                restaurantId,
                pageable
        );

        assertEquals(expectedPage, result);

        verify(repository).findAllUsers(
                searchText,
                null,
                pageable,
                restaurantId
        );

        verifyNoInteractions(roleRepository);
    }

    @Test
    void shouldThrowExceptionWhenRoleDoesNotExist() {
        String searchText = "Marcus";
        RoleName roleName = RoleName.WAITER;
        Long restaurantId = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        when(roleRepository.findByName(roleName))
                .thenReturn(Optional.empty());

        assertThrows(
                RoleNotFoundException.class,
                () -> service.getPageUserUseCase(
                        searchText,
                        roleName,
                        restaurantId,
                        pageable
                )
        );

        verify(roleRepository).findByName(roleName);
        verifyNoInteractions(repository);
    }

    @Test
    void shouldReturnExactlyPageReturnedByRepository() {
        String searchText = "";
        Long restaurantId = 1L;
        Pageable pageable = PageRequest.of(1, 20);

        Page<UserResponse> expectedPage = Page.empty();

        when(repository.findAllUsers(
                searchText,
                null,
                pageable,
                restaurantId
        )).thenReturn(expectedPage);

        Page<UserResponse> result = service.getPageUserUseCase(
                searchText,
                null,
                restaurantId,
                pageable
        );

        assertEquals(expectedPage, result);

        verify(repository).findAllUsers(
                eq(searchText),
                eq(null),
                eq(pageable),
                eq(restaurantId)
        );
    }
}