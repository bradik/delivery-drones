package com.example.deliverydrones.service.impl;

import com.example.deliverydrones.entity.BatteryHistory;
import com.example.deliverydrones.repository.BatteryHistoryRepository;
import com.example.deliverydrones.repository.DroneRepository;
import com.example.deliverydrones.service.BatteryHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BatteryHistoryServiceImpl implements BatteryHistoryService {

    private final DroneRepository droneRepository;
    private final BatteryHistoryRepository batteryHistoryRepository;

    @Override
    public void updateHistory() {
        droneRepository.findAll().forEach(drone -> {
            BatteryHistory batteryHistory = new BatteryHistory();
            batteryHistory.setDrone(drone);
            batteryHistory.setBatteryLevel(drone.getBatteryCapacity());
            batteryHistoryRepository.save(batteryHistory);
        });
    }
}
