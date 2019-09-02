package com.fi1.SensorDataManagement.repository;

import com.fi1.SensorDataManagement.dal.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
