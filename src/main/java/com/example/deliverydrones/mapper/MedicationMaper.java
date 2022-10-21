package com.example.deliverydrones.mapper;

import com.example.deliverydrones.dto.MedicationDto;
import com.example.deliverydrones.entity.Medication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicationMaper {

    MedicationDto toDto(Medication entity);

    Medication toEntity(MedicationDto dto);

}
