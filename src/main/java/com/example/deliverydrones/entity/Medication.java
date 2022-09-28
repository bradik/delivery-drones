package com.example.deliverydrones.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Setter
@Getter
@Entity
public class Medication extends BaseEntity {

    //TODO name (allowed only letters, numbers, ‘-‘, ‘_’);
    @Column(nullable = false)
    private String name;

    @Column(precision = 5, scale = 2)
    private long weight;

    //TODO code (allowed only upper case letters, underscore and numbers);
    @Column(nullable = false)
    private String code;

    @Lob
    private byte[] image;

}


