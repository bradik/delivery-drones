package com.example.deliverydrones.service.impl;

import com.example.deliverydrones.dto.MedicationDto;
import com.example.deliverydrones.mapper.MedicationMaper;
import com.example.deliverydrones.repository.MedicationRepository;
import com.example.deliverydrones.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    private final MedicationMaper medicationMaper;

    @Override
    public List<MedicationDto> getAllMedication(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Order.by("name").ignoreCase()));
        return medicationRepository.findAll(pageable).stream().map(medicationMaper::toDto).collect(Collectors.toList());
    }
}

