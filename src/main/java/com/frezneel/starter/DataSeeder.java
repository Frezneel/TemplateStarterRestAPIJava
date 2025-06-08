package com.frezneel.starter;

import com.frezneel.starter.dto.user.UserRegisterRequest;
import com.frezneel.starter.models.Permissions;
import com.frezneel.starter.models.Roles;
import com.frezneel.starter.models.Users;
import com.frezneel.starter.repositories.PermissionRepo;
import com.frezneel.starter.repositories.RoleRepo;
import com.frezneel.starter.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepo roleRepo;
    private final PermissionRepo permissionRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(RoleRepo roleRepo, PermissionRepo permissionRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Permissions userRead = permissionRepo.findByName("USER_READ").orElseGet(()->
                permissionRepo.save(new Permissions("USER_READ", "Allow reading user information", "User Management")));
        // Admin Seed
        Roles adminRole = roleRepo.findByName("ADMIN").orElseGet(()->
                roleRepo.save(new Roles("ADMIN", "Administrator with Full access")));
        Roles userRole = roleRepo.findByName("USER").orElseGet(()->
                roleRepo.save(new Roles("USER", "Standard user with limited access")));

        // Seed Users
        if (!userRepo.existsByUsername("developer")) {
            Users adminUser = new Users("developer", passwordEncoder.encode("Qwerty@1234"), "admin@example.com", adminRole);
            userRepo.save(adminUser);
        }
    }
}
