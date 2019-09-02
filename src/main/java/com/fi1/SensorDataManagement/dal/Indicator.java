package com.fi1.SensorDataManagement.dal;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(schema = "data")
@Data
public class Indicator {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @Column
    private Integer value;

    @Column
    private Timestamp createdAt;
}
