package com.example.deliverydrones.repository;

import com.example.deliverydrones.entity.Drone;
import com.example.deliverydrones.entity.DroneState;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    Optional<Drone> findBySerialNumber(String serialNumber);

    List<Drone> findAllByState(DroneState state, Pageable pageable);

}
