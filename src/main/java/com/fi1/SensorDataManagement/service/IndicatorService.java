package com.fi1.SensorDataManagement.service;

import com.fi1.SensorDataManagement.dal.Building;
import com.fi1.SensorDataManagement.dal.Indicator;
import com.fi1.SensorDataManagement.model.IndicatorModel;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface IndicatorService {
    Indicator saveIndicator(IndicatorModel indicator);
    List<Indicator> getAllData(Timestamp dateStart, Timestamp dateEnd, Long sensorId);
    List<Indicator> getCurrent(Long buildingId);
    List<Map<Building, Integer>> getAllAverage();
}
