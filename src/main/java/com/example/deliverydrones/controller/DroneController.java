package com.example.deliverydrones.controller;

import com.example.deliverydrones.dto.DroneDto;
import com.example.deliverydrones.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.deliverydrones.util.Constants.API;
import static com.example.deliverydrones.util.Constants.DRONES;

@RestController
@RequestMapping(API + DRONES)
@RequiredArgsConstructor
public class DroneController {

    private final DroneService droneService;

    @PostMapping
    public ResponseEntity<DroneDto> registerDrone(@RequestBody DroneDto dto) {

        droneService.registerDrone(dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto)
                ;
    }

}
