package com.eureka.sso.main.repository;

import com.eureka.sso.main.models.ERole;
import com.eureka.sso.main.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}