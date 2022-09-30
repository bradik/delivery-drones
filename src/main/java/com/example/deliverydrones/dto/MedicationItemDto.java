package com.example.deliverydrones.dto;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MedicationItemDto {

    private MedicationDto medication;

    private int count;
}
