package com.frezneel.starter.repositories;

import com.frezneel.starter.models.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepo extends JpaRepository<Permissions, Long> {
    Optional<Permissions> findByName(String name);
}
