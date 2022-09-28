package com.example.deliverydrones.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class BatteryHistory extends BaseEntity {

    @UpdateTimestamp
    private Date auditTime;

    @ManyToOne
    private Drone drone;

    @Column(nullable = false)
    private int batteryLevel;

}
