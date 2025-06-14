package com.frezneel.starter.controllers;

import com.frezneel.starter.dto.technologyequipment.TechnologyEquipmentRequest;
import com.frezneel.starter.dto.technologyequipment.TechnologyEquipmentResponse;
import com.frezneel.starter.services.TechnologyEquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technology-equipments")
@Tag(name = "Technology Equipment Management", description = "Operations related to Technology equipment inventory")
public class TechnologyEquipmentController {
    private final TechnologyEquipmentService technologyEquipmentService;

    public TechnologyEquipmentController(TechnologyEquipmentService technologyEquipmentService) {
        this.technologyEquipmentService = technologyEquipmentService;
    }

    @Operation(summary = "Create a new Technology equipment record",
        responses = {
                @ApiResponse(responseCode = "201", description = "Equipment created successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input or serial number already exists")
        })
    @PostMapping
    @PreAuthorize("hasAuthority('TECHNOLOGY_EQUIPMENT_CREATE')")
    public ResponseEntity<TechnologyEquipmentResponse> createEquipment(@Valid @RequestBody TechnologyEquipmentRequest request){
        TechnologyEquipmentResponse response = technologyEquipmentService.createEquipment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Technology equipment by ID",
        responses = {
                @ApiResponse(responseCode = "200", description = "Equipment found"),
                @ApiResponse(responseCode = "404", description = "Equipment not found")
        })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TECHNOLOGY_EQUIPMENT_READ')")
    public ResponseEntity<TechnologyEquipmentResponse> getEquipmentById(@PathVariable Long id){
        TechnologyEquipmentResponse response = technologyEquipmentService.getEquipmentById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all office equipments with paging, sorting, and filtering",
            description = "Allows filtering by name, type, brand, and status. Supports pagination and sorting.",
            responses = {
                @ApiResponse(responseCode = "200", description = "List of Technology equipments retrieved successfully")
            }
    )
    @GetMapping
    @PreAuthorize("hasAuthority('TECHNOLOGY_EQUIPMENT_READ')")
    public ResponseEntity<Page<TechnologyEquipmentResponse>> getAllEquipments(
            @Parameter(description = "Filter by equipment name (partial match)") @RequestParam(required = false) String name,
            @Parameter(description = "Filter by equipment type (exact match, case-insensitive") @RequestParam(required = false) String type,
            @Parameter(description = "Filter by equipment brand (exact match, case-insensitive") @RequestParam(required = false) String brand,
            @Parameter(description = "Filter by equipment status (exact match, case-insensitive") @RequestParam(required = false) String status,
            @ParameterObject @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
        Page<TechnologyEquipmentResponse> responsePage = technologyEquipmentService.getAllEquipments(name, type, brand, status, pageable);
        return ResponseEntity.ok(responsePage);
    }

    @Operation(summary = "Update an existing office equipment record by ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Equipment updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or serial number already exists"),
            @ApiResponse(responseCode = "404", description = "Equipment not found")
        })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TECHNOLOGY_EQUIPMENT_UPDATE')")
    public ResponseEntity<TechnologyEquipmentResponse> updateEquipment(@PathVariable Long id, @Valid @RequestBody TechnologyEquipmentRequest request){
        TechnologyEquipmentResponse response = technologyEquipmentService.updateEquipment(id, request);
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Soft delete an office equipment record by ID",
        description = "Sets the isDeleted flag to true, marking the equipment as inactive.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Equipment soft deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Equipment not found"),
        })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TECHNOLOGY_EQUIPMENT_DELETE')")
    public ResponseEntity<TechnologyEquipmentResponse> softDeleteEquipment(@PathVariable Long id){
        TechnologyEquipmentResponse response = technologyEquipmentService.softDeleteEquipment(id);
        return ResponseEntity.ok(response);
    }
}
