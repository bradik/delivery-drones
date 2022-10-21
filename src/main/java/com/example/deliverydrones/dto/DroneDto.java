package com.example.deliverydrones.dto;

import com.example.deliverydrones.entity.DroneModel;
import com.example.deliverydrones.entity.DroneState;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DroneDto {

    private Long id;

    private String serialNumber;

    private DroneModel model;

    @Min(value = 1, message = "weightLimit less than 1 gram")
    @Max(value = 500, message = "weightLimit exceeded 500 grams")
    private long weightLimit;

    private int batteryCapacity;

    private DroneState state;

}
