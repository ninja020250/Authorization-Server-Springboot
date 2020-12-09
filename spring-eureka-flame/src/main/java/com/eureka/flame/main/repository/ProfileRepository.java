package com.eureka.flame.main.repository;

import com.eureka.flame.main.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT new com.eureka.flame.main.models.Profile(id, firstName, lastName)\n" +
            "FROM Profile\n" +
            "WHERE id=?1")
    Optional<Profile> findProfileById(Long id);
}
