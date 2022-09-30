package com.example.deliverydrones.service;

import com.example.deliverydrones.dto.MedicationDto;

import java.util.List;

public interface MedicationService {

    List<MedicationDto> getAllMedication(int pageNumber, int pageSize);

}
