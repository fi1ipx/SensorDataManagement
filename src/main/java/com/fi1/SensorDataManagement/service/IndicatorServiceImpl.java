package com.fi1.SensorDataManagement.service;

import com.fi1.SensorDataManagement.dal.Building;
import com.fi1.SensorDataManagement.dal.Indicator;
import com.fi1.SensorDataManagement.dal.Sensor;
import com.fi1.SensorDataManagement.model.IndicatorModel;
import com.fi1.SensorDataManagement.repository.BuildingRepository;
import com.fi1.SensorDataManagement.repository.IndicatorRepository;
import com.fi1.SensorDataManagement.repository.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IndicatorServiceImpl implements IndicatorService {

    private IndicatorRepository indicatorRepository;
    private SensorRepository sensorRepository;
    private BuildingRepository buildingRepository;

    @Override
    @Transactional
    public Indicator saveIndicator(final IndicatorModel indicator) {
        log.info("saveIndicator {}", indicator);
        return indicatorRepository.save(convertIndicatorFromModel(indicator));
    }

    @Override
    public List<Indicator> getAllData(final Timestamp dateStart,
                                      final Timestamp dateEnd,
                                      final Long sensorId) {
        log.info("getAllData between {} and {}", dateStart, dateEnd);
        return indicatorRepository.getAllByCreatedAndSensor(dateStart, dateEnd, sensorId);
    }

    @Override
    public List<Indicator> getCurrent(final Long buildingId) {
        log.info("getCurrent for building with id = {}", buildingId);
        final List<Sensor> sensors = sensorRepository.getAllByBuilding(buildingRepository.getOne(buildingId));
        return indicatorRepository.getAllCurrent(sensors);
    }

    @Override
    public List<Map<Building, Integer>> getAllAverage() {
        log.info("getAllAverage");
        final List<Map<Building, Integer>> list = new ArrayList<>();
        final List<Building> buildings = buildingRepository.findAll();
        buildings.forEach(b -> {
            final List<Indicator> indicators = getCurrent(b.getId());

            final int size = indicators.size();
            if (size > 0) {
                final int sum = indicators.stream()
                        .map(Indicator::getValue)
                        .mapToInt(Integer::intValue).sum();
                list.add(Collections.singletonMap(b, sum / size));
            } else {
                list.add(Collections.singletonMap(b, null));
            }
        });
        return list;
    }

    private Indicator convertIndicatorFromModel(final IndicatorModel model) {
        final Indicator entity = new Indicator();
        entity.setSensor(sensorRepository.getOne(model.getSensorId()));
        entity.setValue(model.getValue());
        entity.setCreatedAt(model.getCreatedAt());
        return entity;
    }

    @Autowired
    public void setIndicatorRepository(final IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    @Autowired
    public void setSensorRepository(final SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Autowired
    public void setBuildingRepository(final BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }
}
