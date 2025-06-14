package com.frezneel.starter.services;

import com.frezneel.starter.dto.technologyequipment.TechnologyEquipmentRequest;
import com.frezneel.starter.dto.technologyequipment.TechnologyEquipmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface TechnologyEquipmentService {
    @Transactional
    TechnologyEquipmentResponse createEquipment(TechnologyEquipmentRequest request);
    @Transactional(readOnly = true)
    TechnologyEquipmentResponse getEquipmentById(Long id);
    @Transactional(readOnly = true)
    Page<TechnologyEquipmentResponse> getAllEquipments(String name, String type, String brand, String status, Pageable pageable);
    @Transactional
    TechnologyEquipmentResponse updateEquipment(Long id, TechnologyEquipmentRequest request);
    @Transactional
    TechnologyEquipmentResponse softDeleteEquipment(Long id);
}
