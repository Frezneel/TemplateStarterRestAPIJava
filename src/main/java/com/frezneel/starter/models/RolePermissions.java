package com.frezneel.starter.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "role_permissions")
public class RolePermissions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY loading lebih efisien
    @JoinColumn(name = "role_id", nullable = false)
    private Roles roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permissions permissions;

    public RolePermissions() {
        super();
    }

    public RolePermissions(Roles roles, Permissions permissions) {
        super();
        this.roles = roles;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissions that = (RolePermissions) o;
        return Objects.equals(roles, that.roles) &&
                Objects.equals(permissions, that.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roles, permissions);
    }
}
