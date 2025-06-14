package com.frezneel.starter.dto.technologyequipment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TechnologyEquipmentResponse {
    private Long id;
    private String name;
    private String serialNumber;
    private String type;
    private String brand;
    private Integer quantity;
    private String imgSrc;
    private String notes;
    private String location;
    private String status;
    private LocalDate acuisitionDate;
    private LocalDate warrantyEndDate;
    private String createdBy;
    private LocalDateTime createdOn;
    private String modifiedBy;
    private LocalDateTime modifiedOn;
    private Boolean isDeleted;

    public TechnologyEquipmentResponse() {
    }

    public TechnologyEquipmentResponse(Long id, String name, String serialNumber, String type, String brand, Integer quantity, String imgSrc, String notes, String location, String status, LocalDate acuisitionDate, LocalDate warrantyEndDate, String createdBy, LocalDateTime createdOn, String modifiedBy, LocalDateTime modifiedOn, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.type = type;
        this.brand = brand;
        this.quantity = quantity;
        this.imgSrc = imgSrc;
        this.notes = notes;
        this.location = location;
        this.status = status;
        this.acuisitionDate = acuisitionDate;
        this.warrantyEndDate = warrantyEndDate;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.modifiedBy = modifiedBy;
        this.modifiedOn = modifiedOn;
        this.isDeleted = isDeleted;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAcuisitionDate() {
        return acuisitionDate;
    }

    public void setAcuisitionDate(LocalDate acuisitionDate) {
        this.acuisitionDate = acuisitionDate;
    }

    public LocalDate getWarrantyEndDate() {
        return warrantyEndDate;
    }

    public void setWarrantyEndDate(LocalDate warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
