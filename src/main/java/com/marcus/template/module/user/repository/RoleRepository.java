package com.marcus.template.module.user.repository;

import com.marcus.template.module.user.entity.Role;
import com.marcus.template.module.user.enuns.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
