package com.example.deliverydrones.service.impl;

import com.example.deliverydrones.dto.DroneDto;
import com.example.deliverydrones.entity.Drone;
import com.example.deliverydrones.mapper.DroneMapper;
import com.example.deliverydrones.repository.DroneRepository;
import com.example.deliverydrones.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;

    @Override
    public DroneDto registerDrone(DroneDto dto) {

        Optional<Drone> opDrone = droneRepository.findBySerialNumber(dto.getSerialNumber());
        if (opDrone.isEmpty()) {
            Drone entity = droneMapper.toEntity(dto);
            droneRepository.save(entity);
            dto = droneMapper.toDto(entity);
        } else {
            //TODO we need to consider the error
            //dto.setId(opDrone.get().getId());
        }

        return dto;
    }
}
