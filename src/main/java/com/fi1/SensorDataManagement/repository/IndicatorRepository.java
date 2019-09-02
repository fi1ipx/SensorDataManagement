package com.fi1.SensorDataManagement.repository;

import com.fi1.SensorDataManagement.dal.Indicator;
import com.fi1.SensorDataManagement.dal.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
    @Query(nativeQuery = true,
            value = "select * from data.indicator where created_at >= :dateStart " +
                    "and created_at <= :dateEnd and sensor_id = :sensorId")
    List<Indicator> getAllByCreatedAndSensor(
            @Param("dateStart") Timestamp dateStart,
            @Param("dateEnd") Timestamp dateEnd,
            @Param("sensorId") Long sensorId);

    @Query(nativeQuery = true,
            value = "select * from data.indicator where sensor_id in :sensors")
    List<Indicator> getAllBySensors(@Param("sensors") List<Sensor> sensors);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT ON (sensor_id) id, sensor_id, created_at, value\n" +
                    "FROM data.indicator where sensor_id in :sensors\n" +
                    "ORDER  BY sensor_id, \"created_at\" DESC;")
    List<Indicator> getAllCurrent(@Param("sensors") List<Sensor> sensors);
}
