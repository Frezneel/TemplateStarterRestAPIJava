package com.frezneel.starter.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "roles")
public class Roles extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    // Cascade.ALL: operasi pada Role akan berpengaruh pada RolePermission
    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolePermissions> rolePermissions = new HashSet<>();

    public Roles(){
        super();
    }

    public Roles(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RolePermissions> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermissions> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public void addPermission(Permissions permission){
        RolePermissions rolePermissions = new RolePermissions(this, permission);
        this.rolePermissions.add(rolePermissions);
        permission.getRolePermissions().add(rolePermissions);
    }

    public void removePermission(Permissions permissions){
        RolePermissions rolePermissionsToRemove = null;
        for (RolePermissions rp : this.rolePermissions){
            if (rp.getPermissions().equals(permissions)){
                rolePermissionsToRemove = rp;
                break;
            }
        }
        if (rolePermissions != null){
            this.rolePermissions.remove(rolePermissionsToRemove);
            permissions.getRolePermissions().remove(rolePermissionsToRemove);
        }
    }
}
