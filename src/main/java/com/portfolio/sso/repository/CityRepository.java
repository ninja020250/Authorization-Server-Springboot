package com.portfolio.sso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.sso.models.City;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
}
