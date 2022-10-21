package com.example.deliverydrones.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MedicationDto {

    private Long id;

    @Pattern(regexp = "[\\w-]*", message = "allowed only letters, numbers, ‘-‘, ‘_’")
    private String name;

    @Min(value = 1, message = "weightLimit less than 1 gram")
    private long weight;

    @Pattern(regexp = "[A-Z_\\d]*", message = "allowed only upper case letters, underscore and numbers")
    private String code;

    private String imageUrl;

}
