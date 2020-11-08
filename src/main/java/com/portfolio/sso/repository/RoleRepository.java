package com.portfolio.sso.repository;

import java.util.Optional;

import com.portfolio.sso.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.sso.models.ERole;
import com.portfolio.sso.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}