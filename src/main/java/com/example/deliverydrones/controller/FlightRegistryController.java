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

    @PostMapping("/register/{serialNumber}")
    public ResponseEntity<FlightRegistryDto> registerFlight(@PathVariable String serialNumber, @RequestBody(required = false) List<MedicationItemDto> dtos) {
        FlightRegistryDto flightRegistryDto = flightRegistryService.registerFlight(serialNumber, dtos);
        return ResponseEntity.ok(flightRegistryDto);
    }

    @PostMapping("/medication/add/{flightId}")
    public ResponseEntity<FlightRegistryDto> addMedicationToFlight(@PathVariable Long flightId, @RequestBody(required = false) List<MedicationItemDto> dtos) {
        FlightRegistryDto flightRegistryDto = flightRegistryService.addMedicationToFlight(flightId, dtos);
        return ResponseEntity.ok(flightRegistryDto);
    }

    @GetMapping("/drone/{serialNumber}")
    public ResponseEntity<FlightRegistryDto> findLastByDrone(@PathVariable String serialNumber) {
        FlightRegistryDto flightRegistryDto = flightRegistryService.findLastByDrone(serialNumber);
        return ResponseEntity.ok(flightRegistryDto);
    }
}
