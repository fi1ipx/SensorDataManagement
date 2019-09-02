package com.fi1.SensorDataManagement.controller;

import com.fi1.SensorDataManagement.dal.Building;
import com.fi1.SensorDataManagement.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping(value = {"/", ""})
    public List<Building> index() {
        return buildingRepository.findAll();
    }
}
