package com.example.deliverydrones.service;

import com.example.deliverydrones.dto.FlightRegistryDto;
import com.example.deliverydrones.dto.MedicationItemDto;

import java.util.List;

public interface FlightRegistryService {

    FlightRegistryDto registerFlight(String serialNumber);

    FlightRegistryDto registerFlight(String serialNumber, List<MedicationItemDto> dto);

    FlightRegistryDto addMedicationToFlight(String serialNumber, List<MedicationItemDto> dto);

    List<MedicationItemDto> getMedicationItemsBySerialNumber(String serialNumber);
}
