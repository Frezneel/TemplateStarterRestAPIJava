package com.frezneel.starter.repositories;

import com.frezneel.starter.models.TechnologyEquipments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TechnologyEquipmentRepo extends JpaRepository<TechnologyEquipments, Long>, JpaSpecificationExecutor<TechnologyEquipments> {
    Optional<TechnologyEquipments> findBySerialNumber(String serialNumber);
}
