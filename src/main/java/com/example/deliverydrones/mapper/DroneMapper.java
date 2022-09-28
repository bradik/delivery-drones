package com.example.deliverydrones.mapper;

import com.example.deliverydrones.dto.DroneDto;
import com.example.deliverydrones.entity.Drone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    DroneDto toDto(Drone drone);

    Drone toEntity(DroneDto dto);

}
