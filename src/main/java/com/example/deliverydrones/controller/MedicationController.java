package com.example.deliverydrones.controller;

import com.example.deliverydrones.dto.DroneDto;
import com.example.deliverydrones.dto.MedicationDto;
import com.example.deliverydrones.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.deliverydrones.util.Constants.API;
import static com.example.deliverydrones.util.Constants.MEDICATIONS;

@RestController
@RequestMapping(API + MEDICATIONS)
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping
    public ResponseEntity<MedicationDto> registerMedication(@Valid @RequestBody MedicationDto dto) {

        MedicationDto result = medicationService.registerMedication(dto);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicationDto>> getAllMedications(
            @RequestParam(required = false, name = "pageNumber", defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, name = "pageSize", defaultValue = "50") Integer pageSize) {

        List<MedicationDto> allMedication = medicationService.getAllMedication(pageNumber, pageSize);

        return ResponseEntity.ok(allMedication);
    }

}
