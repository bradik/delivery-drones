package com.example.deliverydrones.repository;

import com.example.deliverydrones.entity.BatteryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryHistoryRepository extends JpaRepository<BatteryHistory, Long> {

}