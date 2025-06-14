package com.frezneel.starter.dto.technologyequipment;

import com.frezneel.starter.models.TechnologyEquipments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TechnologyEquipmentMapper {
    TechnologyEquipmentMapper INSTANCE = Mappers.getMapper(TechnologyEquipmentMapper.class);

    @Mapping(target = "id", ignore = true) // ID akan digenerate DB
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    TechnologyEquipments toEntity(TechnologyEquipmentRequest dto);

    TechnologyEquipmentResponse toResponseDto(TechnologyEquipments entity);

    @Mapping(target = "id", ignore = true) // ID akan digenerate DB
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    void updateEntityFromDto(TechnologyEquipmentRequest dto, @MappingTarget TechnologyEquipments entity);

}
