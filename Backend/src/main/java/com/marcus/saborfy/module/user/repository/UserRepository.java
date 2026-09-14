package com.marcus.saborfy.module.user.repository;

import com.marcus.saborfy.module.user.dto.response.UserResponse;
import com.marcus.saborfy.module.user.entity.User;
import com.marcus.saborfy.module.user.enuns.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRegistration(String username);
    boolean existsByRegistration(String username);

    @Query("""
    SELECT new com.marcus.saborfy.module.user.dto.response.UserResponse(
        u.id,
        u.restaurantId,
        u.registration,
        u.name,
        u.role.name,
        u.active,
        u.createdAt,
        u.updatedAt
    )
    FROM User u
    WHERE (:search IS NULL OR u.registration LIKE CONCAT('%', :search, '%')  OR u.name LIKE CONCAT('%', :search, '%'))
    AND (:roleId IS NULL OR u.role.id = :roleId)
    AND (u.role.name != "SYSTEM_ADMIN")
    AND (:restaurantId IS NULL OR u.restaurantId = :restaurantId)
    """)
    Page<UserResponse> findAllUsers(
            @Param("search") String searchString,
            @Param("roleId") Long roleId,
            Pageable pageable,
            @Param("restaurantId") Long restaurantId
    );

    @Query("SELECT u.passwordHash from User u WHERE u.id = :id")
    String findPasswordById(Long id);
}
