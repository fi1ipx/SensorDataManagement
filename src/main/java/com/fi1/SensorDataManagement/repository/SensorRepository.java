package com.fi1.SensorDataManagement.repository;

import com.fi1.SensorDataManagement.dal.Building;
import com.fi1.SensorDataManagement.dal.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository  extends JpaRepository<Sensor, Long> {
    List<Sensor> getAllByBuilding(Building building);
}
