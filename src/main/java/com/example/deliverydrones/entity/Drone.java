package com.example.deliverydrones.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Table(name = "drones")
@Entity
public class Drone extends BaseEntity {
    @Column(name = "serial_number", unique = true, length = 100, nullable = false)
    private String serialNumber;

    @Column(name = "battery_capacity", precision = 100, scale = 2)
    private double batteryCapacity;
}
