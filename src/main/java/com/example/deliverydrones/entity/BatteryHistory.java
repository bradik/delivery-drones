package com.example.deliverydrones.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BatteryHistory extends BaseEntity {

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditTime;

    @ManyToOne
    private Drone drone;

    @Column(nullable = false)
    private int batteryLevel;

}

