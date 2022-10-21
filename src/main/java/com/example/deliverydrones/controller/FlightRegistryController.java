package com.example.deliverydrones.controller;

import com.example.deliverydrones.dto.FlightRegistryDto;
import com.example.deliverydrones.dto.MedicationItemDto;
import com.example.deliverydrones.service.FlightRegistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.deliverydrones.util.Constants.API;
import static com.example.deliverydrones.util.Constants.FLIGHTS;

@RestController
@RequestMapping(API + FLIGHTS)
@RequiredArgsConstructor
public class FlightRegistryController {

    private final FlightRegistryService flightRegistryService;

    @PostMapping("/register/drone/{serialNumber}")
    public ResponseEntity<FlightRegistryDto> registerFlight(
            @PathVariable String serialNumber, @RequestBody(required = false) List<MedicationItemDto> dtos) {
        FlightRegistryDto flightRegistryDto = flightRegistryService.registerFlight(serialNumber, dtos);
        return ResponseEntity.ok(flightRegistryDto);
    }

    @PostMapping("/medication/add/drone/{serialNumber}")
    public ResponseEntity<FlightRegistryDto> addMedicationToFlight(
            @PathVariable String serialNumber, @RequestBody(required = false) List<MedicationItemDto> dtos) {
        FlightRegistryDto flightRegistryDto = flightRegistryService.addMedicationToFlight(serialNumber, dtos);
        return ResponseEntity.ok(flightRegistryDto);
    }

    @GetMapping("/medication/drone/{serialNumber}")
    public ResponseEntity<List<MedicationItemDto>> getMedicationItemsBySerialNumber(@PathVariable String serialNumber) {
        List<MedicationItemDto> medicationItemDtos = flightRegistryService.getMedicationItemsBySerialNumber(serialNumber);
        return ResponseEntity.ok(medicationItemDtos);
    }
}
