package com.example.deliverydrones.dto;

import com.example.deliverydrones.entity.Drone;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FlightRegistryDto {

    private Long id;

    private Date registrationTime;

    private Date deliveryTime;

    private Drone drone;

    private List<MedicationItemDto> medicationItems;

    private Long totalWeight;
}
