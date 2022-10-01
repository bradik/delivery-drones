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

    Drone toEntity(DroneDto dto);

    List<DroneDto> toEntities(List<Drone> all);
}
