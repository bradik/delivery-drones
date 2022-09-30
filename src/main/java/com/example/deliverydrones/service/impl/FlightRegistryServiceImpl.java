package com.example.deliverydrones.service.impl;

import com.example.deliverydrones.dto.FlightRegistryDto;
import com.example.deliverydrones.dto.MedicationItemDto;
import com.example.deliverydrones.entity.Drone;
import com.example.deliverydrones.entity.FlightRegistry;
import com.example.deliverydrones.entity.FlightState;
import com.example.deliverydrones.entity.MedicationItem;
import com.example.deliverydrones.error.DroneException;
import com.example.deliverydrones.error.ErrorCode;
import com.example.deliverydrones.mapper.FlightRegistryMapper;
import com.example.deliverydrones.mapper.MedicationItemMaper;
import com.example.deliverydrones.repository.DroneRepository;
import com.example.deliverydrones.repository.FlightRegistryRepository;
import com.example.deliverydrones.service.FlightRegistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightRegistryServiceImpl implements FlightRegistryService {

    private final MedicationItemMaper medicationItemMaper;
    private final FlightRegistryMapper flightRegistryMapper;

    private final DroneRepository droneRepository;
    private final FlightRegistryRepository flightRegistryRepository;

    @Override
    public FlightRegistryDto registerFlight(String serialNumber) {
        return registerFlight(serialNumber, null);
    }

    @Override
    public FlightRegistryDto registerFlight(String serialNumber, List<MedicationItemDto> dtos) {

        Drone drone = droneRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> DroneException.of(ErrorCode.DR_01));


        FlightRegistry registryEntity = new FlightRegistry();
        registryEntity.setDrone(drone);
        registryEntity.setStatus(FlightState.NEW);

        addMedicationToFlight(registryEntity, dtos);

        registryEntity = flightRegistryRepository.save(registryEntity);

        return flightRegistryMapper.toDto(registryEntity);
    }

    @Override
    public FlightRegistryDto addMedicationToFlight(long idFlight, List<MedicationItemDto> dtos) {

        FlightRegistry registryEntity = flightRegistryRepository.findById(idFlight)
                .orElseThrow(() -> DroneException.of(ErrorCode.FRS_02));

        addMedicationToFlight(registryEntity, dtos);

        flightRegistryRepository.flush();

        registryEntity = flightRegistryRepository.save(registryEntity);

        return flightRegistryMapper.toDto(registryEntity);
    }

    @Override
    public FlightRegistryDto findLastByDrone(String serialNumber) {

        Drone drone = droneRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> DroneException.of(ErrorCode.DR_01));

        FlightRegistry flightRegistry = flightRegistryRepository.findFirstByDroneAndStatusOrderByIdDesc(drone, FlightState.NEW);

        return flightRegistryMapper.toDto(flightRegistry);
    }

    private void addMedicationToFlight(FlightRegistry registryEntity, List<MedicationItemDto> newDtos) {
        if (newDtos == null) {
            return;
        }

        List<MedicationItemDto> medicationItemDtos = new ArrayList<>();

        Optional.ofNullable(registryEntity.getMedicationItems())
                .map(Collection::stream)
                .map(it -> it.map(medicationItemMaper::toDto))
                .map(it -> it.collect(Collectors.toList()))
                .ifPresent(medicationItemDtos::addAll);

        List<MedicationItemDto> existItems = new ArrayList<>();

        for (MedicationItemDto item : medicationItemDtos) {

            List<MedicationItemDto> items = newDtos.stream()
                    .filter(it -> item.getMedication().equals(it.getMedication()))
                    .collect(Collectors.toList());

            items.forEach(it -> item.setCount(item.getCount() + it.getCount()));

            existItems.addAll(items);
        }

        newDtos.removeAll(existItems);

        medicationItemDtos.addAll(newDtos);

        Long totalWeight = medicationItemDtos.stream()
                .map(medicationItem -> medicationItem.getMedication().getWeight() * medicationItem.getCount())
                .reduce(Long::sum)
                .orElse(0L);

        long droneWeightLimit = registryEntity.getDrone().getWeightLimit();

        if (totalWeight > droneWeightLimit) {
            throw DroneException.of(ErrorCode.FRS_03);
        }

        List<MedicationItem> medicationItem = medicationItemDtos.stream()
                .map(medicationItemMaper::toEntity)
                .collect(Collectors.toList());

        registryEntity.setMedicationItems(medicationItem);
        registryEntity.setTotalWeight(totalWeight);
    }
}
