package com.example.deliverydrones.repository;

import com.example.deliverydrones.entity.MedicationItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationItemRepository extends JpaRepository<MedicationItem, Long> {
}