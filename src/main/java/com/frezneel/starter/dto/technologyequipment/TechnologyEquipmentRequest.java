package com.frezneel.starter.dto.technologyequipment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TechnologyEquipmentRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Schema(description = "Name of the equipment", example = "Dell UltraSharp Monitor")
    private String name;

    @Schema(description = "Unique serial number of the equipment", example = "SN-A1B2C3D4")
    private String serialNumber;

    @NotBlank(message = "Type is required")
    @Size(max = 50, message = "Type cannot exceed 50 characters")
    @Schema(description = "Type of the equipment (e.g., Monitor, Printer, Keyboard)", example = "Monitor")
    private String type;

    @Size(max = 50, message = "Brand cannot exceed 50 characters")
    @Schema(description = "Brand of the equipment (e.g., Dell, HP, Logitech)", example = "Dell")
    private String brand;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Schema(description = "Quantity of the equipment", example = "5")
    private Integer quantity;

    @Schema(description = "URL of the equipment image", example = "http://example.com/monitor.jpg")
    private String imgSrc;

    @Schema(description = "Additional notes about the equipment", example = "Purchased in bulk")
    private String notes;

    @Size(max = 100, message = "Location cannot exceed 100 characters")
    @Schema(description = "Physical location of the equipment", example = "Meeting Room A")
    private String location;

    @NotBlank(message = "Status is required")
    @Size(max = 20, message = "Status cannot exceed 20 characters")
    @Schema(description = "Current status of the equipment (e.g., Available, In Use, Under Maintenance", example = "Available")
    private String status;

    @Schema(description = "Date of equipment acquisition", example = "2023-01-15")
    private LocalDate acquisitionDate;

    @Schema(description = "Date when the equipment warranty ends", example = "2030-01-15")
    private LocalDate warrantyEndDate;

    public TechnologyEquipmentRequest() {}

    public TechnologyEquipmentRequest(String name, String serialNumber, String type, String brand, Integer quantity, String imgSrc, String notes, String location, String status, LocalDate acquisitionDate, LocalDate warrantyEndDate) {
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
}
