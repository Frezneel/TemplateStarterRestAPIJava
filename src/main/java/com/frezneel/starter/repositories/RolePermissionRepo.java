package com.frezneel.starter.repositories;

import com.frezneel.starter.models.RolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepo extends JpaRepository<RolePermissions, Long> {

}
