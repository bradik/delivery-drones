package com.example.deliverydrones.dto;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MedicationDto {

    private Long id;

    private String name;

    private long weight;

    private String code;

    private String imageUrl;

}
