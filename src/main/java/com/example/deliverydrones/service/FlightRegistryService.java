package com.example.deliverydrones.service;

import com.example.deliverydrones.dto.FlightRegistryDto;
import com.example.deliverydrones.dto.MedicationItemDto;

import java.util.List;

public interface FlightRegistryService {

    FlightRegistryDto registerFlight(String serialNumber);

    FlightRegistryDto registerFlight(String serialNumber, List<MedicationItemDto> dto);

    FlightRegistryDto addMedicationToFlight(long idFlight, List<MedicationItemDto> dto);

    FlightRegistryDto findLastByDrone(String serialNumber);
}
