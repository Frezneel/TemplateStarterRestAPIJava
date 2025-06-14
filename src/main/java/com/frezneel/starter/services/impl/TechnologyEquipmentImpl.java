package com.frezneel.starter.services.impl;

import com.frezneel.starter.dto.technologyequipment.TechnologyEquipmentMapper;
import com.frezneel.starter.dto.technologyequipment.TechnologyEquipmentRequest;
import com.frezneel.starter.dto.technologyequipment.TechnologyEquipmentResponse;
import com.frezneel.starter.exceptions.ResourceNotFoundException;
import com.frezneel.starter.models.TechnologyEquipments;
import com.frezneel.starter.repositories.TechnologyEquipmentRepo;
import com.frezneel.starter.services.TechnologyEquipmentService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyEquipmentImpl implements TechnologyEquipmentService {

    private final TechnologyEquipmentRepo technologyEquipmentRepo;
    private final TechnologyEquipmentMapper technologyEquipmentMapper;

    public TechnologyEquipmentImpl(TechnologyEquipmentRepo technologyEquipmentRepo, TechnologyEquipmentMapper technologyEquipmentMapper) {
        this.technologyEquipmentRepo = technologyEquipmentRepo;
        this.technologyEquipmentMapper = technologyEquipmentMapper;
    }

    @Override
    public TechnologyEquipmentResponse createEquipment(TechnologyEquipmentRequest request) {
        if (technologyEquipmentRepo.findBySerialNumber(request.getSerialNumber()).isPresent()){
            throw new IllegalArgumentException("Equipment with serial number " + request.getSerialNumber());
        }
        TechnologyEquipments equipment = technologyEquipmentMapper.toEntity(request);
        equipment.setIsDeleted(false);
        TechnologyEquipments savedEquipment = technologyEquipmentRepo.save(equipment);
        return technologyEquipmentMapper.toResponseDto(savedEquipment);
    }

    @Override
    public TechnologyEquipmentResponse getEquipmentById(Long id) {
        TechnologyEquipments equipment = technologyEquipmentRepo.findById(id)
                .filter(eq -> !eq.getIsDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Office Equipment not found with id: " + id));
        return technologyEquipmentMapper.toResponseDto(equipment);
    }

    @Override
    public Page<TechnologyEquipmentResponse> getAllEquipments(String name, String type, String brand, String status, Pageable pageable) {
        Specification<TechnologyEquipments> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // FIlter berdasarkan isDeleted
            predicates.add(cb.isFalse(root.get("isDeleted")));

            if (name != null && !name.isEmpty()){
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (type != null && !type.isEmpty()){
                predicates.add(cb.equal(cb.lower(root.get("type")), type.toLowerCase()));
            }
            if (brand != null && !brand.isEmpty()){
                predicates.add(cb.equal(cb.lower(root.get("brand")), brand.toLowerCase()));
            }
            if (status != null && !status.isEmpty()){
                predicates.add(cb.equal(cb.lower(root.get("status")), status.toLowerCase()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<TechnologyEquipments> requipmentPage = technologyEquipmentRepo.findAll(spec, pageable);
        return requipmentPage.map(technologyEquipmentMapper::toResponseDto);
    }

    @Override
    public TechnologyEquipmentResponse updateEquipment(Long id, TechnologyEquipmentRequest request) {
        TechnologyEquipments existingEquipment = technologyEquipmentRepo.findById(id)
                .filter(eq -> !eq.getIsDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Office Equipment not found with id: " + id));

        if (request.getSerialNumber() != null && !request.getSerialNumber().equals(existingEquipment.getSerialNumber())){
            if (technologyEquipmentRepo.findBySerialNumber(request.getSerialNumber()).isPresent()){
                throw new IllegalArgumentException("Equipment with serial number " + request.getSerialNumber() + " already exists.");
            }
        }

        technologyEquipmentMapper.updateEntityFromDto(request, existingEquipment);
        TechnologyEquipments updatedEquipment = technologyEquipmentRepo.save(existingEquipment);
        return technologyEquipmentMapper.toResponseDto(updatedEquipment);
    }

    @Override
    public TechnologyEquipmentResponse softDeleteEquipment(Long id) {
        TechnologyEquipments equipment = technologyEquipmentRepo.findById(id)
                .filter(eq -> !eq.getIsDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Office Equipment not found with id : " + id));
        equipment.setIsDeleted(true);
        TechnologyEquipments deletedEquipment = technologyEquipmentRepo.save(equipment);
        return technologyEquipmentMapper.toResponseDto(deletedEquipment);
    }
}
