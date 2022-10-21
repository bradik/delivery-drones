package com.example.deliverydrones.dto;

import com.example.deliverydrones.entity.Drone;
import com.example.deliverydrones.entity.FlightState;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FlightRegistryDto {

    private Date registrationTime;

    private Date deliveryTime;

    private DroneDto drone;

    private List<MedicationItemDto> medicationItems;

    private Long totalWeight;

    private FlightState state;
}
