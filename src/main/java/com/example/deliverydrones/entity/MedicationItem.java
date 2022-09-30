package com.example.deliverydrones.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity
public class MedicationItem extends BaseEntity {

    @ManyToOne(cascade =  CascadeType.REFRESH)
    @JoinColumn(name = "medication_id")
    private Medication medication;

    private int count;
}
