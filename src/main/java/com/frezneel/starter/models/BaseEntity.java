package com.frezneel.starter.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "created_by", nullable = false, updatable = false)
    protected String createdBy;
    @Column(name = "created_on", nullable = false, updatable = false)
    protected LocalDateTime createdOn;
    @Column(name = "modified_by")
    protected String modifiedBy;
    @Column(name = "modified_on")
    protected LocalDateTime modifiedOn;
    @Column(name = "is_deleted", nullable = false)
    protected Boolean isDeleted = false;

    public BaseEntity() {
        this.createdOn = LocalDateTime.now();
        this.isDeleted = false;
    }

    @PrePersist
    protected void onCreate(){
        this.createdOn = LocalDateTime.now();
        if (this.createdBy == null || this.createdBy.isEmpty()){
            this.createdBy = "SYSTEM";
        }
        this.isDeleted = false;
    }

    @PreUpdate
    protected void onModified(){
        this.modifiedOn = LocalDateTime.now();
        if (this.modifiedBy == null || this.modifiedBy.isEmpty()){
            this.modifiedBy = "SYSTEM";
        }
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
