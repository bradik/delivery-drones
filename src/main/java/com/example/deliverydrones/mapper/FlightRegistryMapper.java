package com.example.deliverydrones.mapper;

import com.example.deliverydrones.dto.FlightRegistryDto;
import com.example.deliverydrones.entity.FlightRegistry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses  = {DroneMapper.class, MedicationItemMaper.class})
public interface FlightRegistryMapper {

    FlightRegistryDto toDto(FlightRegistry entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "drone.currentFlight", ignore = true)
    FlightRegistry toEntity(FlightRegistryDto dto);
}
