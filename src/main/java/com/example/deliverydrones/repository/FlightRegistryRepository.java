package com.example.deliverydrones.repository;

import com.example.deliverydrones.entity.Drone;
import com.example.deliverydrones.entity.FlightRegistry;
import com.example.deliverydrones.entity.FlightState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRegistryRepository extends JpaRepository<FlightRegistry, Long> {
    FlightRegistry findFlightRegistryByDroneSerialNumber(String serialNumber);
}
