package com.frezneel.starter.repositories;

import com.frezneel.starter.models.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername (String username);
    Optional<Users> findByEmail (String email);
    boolean existsByUsername (String username);
    boolean existsByEmail (String email);

    @Query("SELECT u FROM Users u JOIN FETCH u.roles r LEFT JOIN FETCH r.rolePermissions rp LEFT JOIN FETCH rp.permissions WHERE u.username = :username")
    Optional<Users> findByUsernameWithRolesAndPermissions(String username);
}
