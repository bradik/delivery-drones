package com.example.deliverydrones.controller;

import com.example.deliverydrones.dto.DroneDto;
import com.example.deliverydrones.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.deliverydrones.util.Constants.API;
import static com.example.deliverydrones.util.Constants.DRONES;

@RestController
@RequestMapping(API + DRONES)
@RequiredArgsConstructor
public class DroneController {

    private final DroneService droneService;

    @PostMapping
    public ResponseEntity<DroneDto> registerDrone(@Valid @RequestBody DroneDto dto) {

        DroneDto result = droneService.registerDrone(dto);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/availableForLoading")
    public ResponseEntity<List<DroneDto>> getAvailableDronesForLoading(
            @RequestParam(required = false, name = "pageNumber", defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, name = "pageSize", defaultValue = "50") Integer pageSize) {

        List<DroneDto> droneDtos = droneService.getAvailableDronesForLoading(pageNumber, pageSize);

        return ResponseEntity.ok(droneDtos);
    }

    @GetMapping("/battery/{serialNumber}")
    public ResponseEntity<Integer> getBatteryLevel(@PathVariable String serialNumber) {

        DroneDto droneDto = droneService.getDroneBySerialNumber(serialNumber);
        return ResponseEntity.ok(droneDto.getBatteryCapacity());
    }

}
