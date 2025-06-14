package com.frezneel.starter.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "technology_equipment")
public class TechnologyEquipments extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "serial_number", unique = true, length = 50)
    private String serialNumber;
    @Column(name = "type", nullable = false, length = 50)
    private String type;
    @Column(name = "brand", length = 50)
    private String brand;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "img_src", length = 255)
    private String imgSrc;
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    @Column(name = "location", length = 100)
    private String location;
    @Column(name = "status", nullable = false, length = 20)
    private String status;
    @Column(name = "acquisition_date")
    private LocalDate acquisitionDate;
    @Column(name = "warranty_end_date")
    private LocalDate warrantyEndDate;

    public TechnologyEquipments() {
        super();
    }

    public TechnologyEquipments(String name, String serialNumber, String type, String brand, Integer quantity, String imgSrc, String notes, String location, String status, LocalDate acquisitionDate, LocalDate warrantyEndDate) {
        super();
        this.name = name;
        this.serialNumber = serialNumber;
        this.type = type;
        this.brand = brand;
        this.quantity = quantity;
        this.imgSrc = imgSrc;
        this.notes = notes;
        this.location = location;
        this.status = status;
        this.acquisitionDate = acquisitionDate;
        this.warrantyEndDate = warrantyEndDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public LocalDate getWarrantyEndDate() {
        return warrantyEndDate;
    }

    public void setWarrantyEndDate(LocalDate warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnologyEquipments that = (TechnologyEquipments) o;
        return Objects.equals(id, that.id) && Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber);
    }
}
