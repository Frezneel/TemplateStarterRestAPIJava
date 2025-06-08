package com.frezneel.starter.repositories;

import com.frezneel.starter.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername (String username);
    Optional<Users> findByEmail (String email);
    boolean existsByUsername (String username);
    boolean existsByEmail (String email);

}
