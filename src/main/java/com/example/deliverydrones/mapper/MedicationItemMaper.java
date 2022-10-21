package com.example.deliverydrones.mapper;

import com.example.deliverydrones.dto.MedicationItemDto;
import com.example.deliverydrones.entity.MedicationItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationItemMaper {

    MedicationItemDto toDto(MedicationItem entity);
    List<MedicationItemDto> toDtos(List<MedicationItem> entity);

    @Mapping(target = "id", ignore = true)
    MedicationItem toEntity(MedicationItemDto dto);

}
