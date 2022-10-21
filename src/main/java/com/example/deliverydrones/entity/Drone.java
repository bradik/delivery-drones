package com.example.deliverydrones.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
public class Drone extends BaseEntity {

    @NotNull
    @Column(unique = true, length = 100)
    private String serialNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Column(precision = 5, scale = 2)
    private long weightLimit;

    @Column(precision = 5, scale = 2)
    private int batteryCapacity;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DroneState state;

    @OneToOne
    @JoinColumn(name = "current_flight_id")
    private FlightRegistry currentFlight;

}
