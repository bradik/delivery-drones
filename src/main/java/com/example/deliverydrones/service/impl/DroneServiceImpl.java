package com.example.deliverydrones.service.impl;

import com.example.deliverydrones.dto.DroneDto;
import com.example.deliverydrones.entity.Drone;
import com.example.deliverydrones.entity.DroneState;
import com.example.deliverydrones.error.DroneException;
import com.example.deliverydrones.error.ErrorCode;
import com.example.deliverydrones.mapper.DroneMapper;
import com.example.deliverydrones.repository.DroneRepository;
import com.example.deliverydrones.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;

    @Value("${app.battery.percentage.min}")
    private int minBatteryCapacity;

    @Override
    public List<DroneDto> getAllDrones() {

        return droneMapper.toEntities(droneRepository.findAll());
    }

    @Override
    public DroneDto getDroneBySerialNumber(String serialNumber) {

        Drone drone = droneRepository.findBySerialNumber(serialNumber).orElseThrow(() -> DroneException.of(ErrorCode.DR_01));
        return droneMapper.toDto(drone);
    }

    @Override
    public DroneDto registerDrone(DroneDto dto) {

        Drone entity = droneRepository.save(droneMapper.toEntity(dto));
        return droneMapper.toDto(entity);
    }

    @Override
    public List<DroneDto> getAvailableDronesForLoading(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Order.by("id").ignoreCase()));

        List<Drone> drones = droneRepository.findAllByState(DroneState.IDLE, pageable)
                .stream()
                .filter(drone -> drone.getBatteryCapacity() > minBatteryCapacity)
                .collect(Collectors.toList());

        return drones
                .stream()
                .map(droneMapper::toDto)
                .collect(Collectors.toList());
    }
}
