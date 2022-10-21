package com.example.deliverydrones.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(indexes = @Index(columnList ="name, weight"))
public class Medication extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(precision = 5, scale = 2)
    private long weight;

    @Column(nullable = false)
    private String code;

    private String imageUrl;

}


