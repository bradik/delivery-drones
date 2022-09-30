package com.example.deliverydrones.dto;

import com.example.deliverydrones.entity.DroneModel;
import com.example.deliverydrones.entity.DroneState;
import lombok.*;


@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DroneDto {

    private String serialNumber;

    private DroneModel model;

    private long weightLimit;

    private int batteryCapacity;

    private DroneState state;
}
