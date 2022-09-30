package com.example.deliverydrones.service;

import com.example.deliverydrones.dto.DroneDto;

import java.util.List;


public interface DroneService {

    List<DroneDto> getAvailableDronesForLoading(int pageNumber, int pageSize);

    Boolean registerDrone(DroneDto droneDto);

    DroneDto getDroneBySerialNumber(String serialNumber);

    List<DroneDto> getAllDrones();
}
