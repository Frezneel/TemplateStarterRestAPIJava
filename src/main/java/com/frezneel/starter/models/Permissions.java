package com.frezneel.starter.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class Permissions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;
    @Column(name = "description", length = 255)
    private String description;
    @Column(name = "category",length = 50)
    private String category;

    @OneToMany(mappedBy = "permissions", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolePermissions> rolePermissions = new HashSet<>();

    public Permissions(){
        super();
    }

    public Permissions(String name, String description, String category) {
        super();
        this.name = name;
        this.description = description;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<RolePermissions> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermissions> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissions that = (Permissions) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
