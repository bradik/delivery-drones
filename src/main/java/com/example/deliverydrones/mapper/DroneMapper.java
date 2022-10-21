package com.example.deliverydrones.mapper;

import com.example.deliverydrones.dto.DroneDto;
import com.example.deliverydrones.entity.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    DroneDto toDto(Drone entity);

    @Mapping(target = "currentFlight", ignore = true)
    Drone toEntity(DroneDto dto);

    @Mapping(target = "model", ignore = true)
    @Mapping(target = "weightLimit", ignore = true)
    @Mapping(target = "serialNumber", ignore = true)
    @Mapping(target = "currentFlight", ignore = true)
    void updateEntity(@MappingTarget Drone entity, DroneDto dto);

    List<DroneDto> toEntities(List<Drone> all);
}
