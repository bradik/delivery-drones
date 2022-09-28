package com.example.deliverydrones.dto;

import lombok.*;


@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DroneDto {

    private Long id;

    private String serialNumber;

}
