package com.example.deliverydrones.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class FlightRegistry extends BaseEntity {

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryTime;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<MedicationItem> medicationItems;

    private Long totalWeight;

    @Enumerated(EnumType.STRING)
    private FlightState state;

    @PreUpdate
    public void onPreUpdate() {
        if (state == FlightState.DELIVERED) {
            deliveryTime = new Date();
        }
    }
}
