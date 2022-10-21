package com.example.deliverydrones.mapper;

import com.example.deliverydrones.entity.DroneState;
import com.example.deliverydrones.entity.FlightState;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FlightStateMapper {
    @ValueMapping(target = "LOADING", source = "LOADING")
    @ValueMapping(target = "DELIVERING", source = "DELIVERING")
    @ValueMapping(target = "DELIVERED", source = "DELIVERED")
    @ValueMapping(target = "CANCELLED", source = MappingConstants.ANY_UNMAPPED)
    FlightState toFlightState(DroneState droneState);
}
