package com.ecommerce.nexus.repositories;

import com.ecommerce.nexus.model.AppRole;
import com.ecommerce.nexus.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
