package com.example.deliverydrones.mapper;

import com.example.deliverydrones.dto.FlightRegistryDto;
import com.example.deliverydrones.entity.FlightRegistry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightRegistryMapper {

    FlightRegistryDto toDto(FlightRegistry entity);

    FlightRegistry toEntity(FlightRegistryDto dto);
}
