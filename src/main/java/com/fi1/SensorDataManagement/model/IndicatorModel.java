package com.fi1.SensorDataManagement.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class IndicatorModel {
    private Long buildingId;
    private Long sensorId;
    private Timestamp createdAt;
    private Integer value;
}
