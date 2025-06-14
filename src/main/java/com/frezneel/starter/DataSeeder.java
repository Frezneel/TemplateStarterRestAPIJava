package com.frezneel.starter;

import com.frezneel.starter.models.Permissions;
import com.frezneel.starter.models.Roles;
import com.frezneel.starter.models.TechnologyEquipments;
import com.frezneel.starter.models.Users;
import com.frezneel.starter.repositories.PermissionRepo;
import com.frezneel.starter.repositories.RoleRepo;
import com.frezneel.starter.repositories.TechnologyEquipmentRepo;
import com.frezneel.starter.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepo roleRepo;
    private final PermissionRepo permissionRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final TechnologyEquipmentRepo technologyEquipmentRepo;

    public DataSeeder(RoleRepo roleRepo, PermissionRepo permissionRepo, UserRepo userRepo, PasswordEncoder passwordEncoder, TechnologyEquipmentRepo technologyEquipmentRepo) {
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.technologyEquipmentRepo = technologyEquipmentRepo;
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

        // Permissions for office equipment
        Permissions equipmentRead = permissionRepo.findByName("TECHNOLOGY_EQUIPMENT_READ").orElseGet(() ->
                permissionRepo.save(new Permissions("TECHNOLOGY_EQUIPMENT_READ", "Allows reading Technology Equipment records", "Technology Equipment Management")));
        Permissions equipmentCreate = permissionRepo.findByName("TECHNOLOGY_EQUIPMENT_CREATE").orElseGet(() ->
                permissionRepo.save(new Permissions("TECHNOLOGY_EQUIPMENT_CREATE", "Allows creating new office equipment records", "Technology Equipment Management")));
        Permissions equipmentUpdate = permissionRepo.findByName("TECHNOLOGY_EQUIPMENT_UPDATE").orElseGet(() ->
                permissionRepo.save(new Permissions("TECHNOLOGY_EQUIPMENT_UPDATE", "Allows updating office equipment records", "Technology Equipment Management")));
        Permissions equipmentDelete = permissionRepo.findByName("TECHNOLOGY_EQUIPMENT_DELETE").orElseGet(() ->
                permissionRepo.save(new Permissions("TECHNOLOGY_EQUIPMENT_DELETE", "Allows deleting office equipment records (soft delete)", "Technology Equipment Management")));

        // Seed Users
        if (!userRepo.existsByUsername("developer")) {
            Users adminUser = new Users("developer", passwordEncoder.encode("Qwerty@1234"), "admin@example.com", adminRole);
            userRepo.save(adminUser);
        }

        // Assign Permission to roles
        if (adminRole.getRolePermissions().stream().noneMatch(rp -> rp.getPermissions().equals(equipmentRead))){
            adminRole.addPermission(equipmentRead);
            adminRole.addPermission(equipmentCreate);
            adminRole.addPermission(equipmentUpdate);
            adminRole.addPermission(equipmentDelete);
            roleRepo.save(adminRole);
        }

        // Seed office Equipments Data (Ex)
        if (technologyEquipmentRepo.count() == 0){
            technologyEquipmentRepo.save(new TechnologyEquipments("Monitor Dell U2419H", "DELU2419H-001", "Monitor", "Dell", 5,
                    "https://example.com/dell_monitor.jpg", "New batch, good condition.", "Office A, Desk 1-5", "Available", LocalDate.of(2023, 1, 15), LocalDate.of(2026, 1, 15)));
            technologyEquipmentRepo.save(new TechnologyEquipments("HP LaserJet Pro MFP M428fdw", "HPLJMP428-A02", "Printer", "HP", 2,
                    "https://example.com/hp_printer.jpg", "Needs toner refill soon.", "Office B, Printer Room", "In Use", LocalDate.of(2022, 5, 20), LocalDate.of(2025, 5, 20)));
            technologyEquipmentRepo.save(new TechnologyEquipments("Logitech MX Master 3", "LOGMXM3-XYZ", "Mouse", "Logitech", 10,
                    "https://example.com/logi_mouse.jpg", "Wireless mouse.", "Storage Room, Shelf C", "Available", LocalDate.of(2023, 10, 1), LocalDate.of(2024, 10, 1)));
            technologyEquipmentRepo.save(new TechnologyEquipments("Epson EB-X06 Projector", "EPSON-EBX06-P01", "Projector", "Epson", 1,
                    "https://example.com/epson_projector.jpg", "Bulb needs replacement.", "Meeting Room C", "Under Maintenance", LocalDate.of(2021, 3, 10), LocalDate.of(2024, 3, 10)));
            technologyEquipmentRepo.save(new TechnologyEquipments("SanDisk Cruzer Glide USB 3.0", "SANDISK-USB-005", "USB Drive", "SanDisk", 50,
                    "https://example.com/sandisk_usb.jpg", "Standard 64GB USB drive.", "IT Supplies Cabinet", "Available", LocalDate.of(2024, 2, 1), LocalDate.of(2025, 2, 1)));
        }
    }
}
