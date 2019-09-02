package com.fi1.SensorDataManagement.controller;

import com.fi1.SensorDataManagement.model.IndicatorModel;
import com.fi1.SensorDataManagement.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/indicator")
public class IndicatorController {
    private IndicatorService indicatorService;

    @PutMapping(value = {"/", ""})
    public ResponseEntity<Object> saveIndicator(@RequestBody final IndicatorModel indicator) {
        return new ResponseEntity<>(indicatorService.saveIndicator(indicator), HttpStatus.OK);
    }

    @GetMapping(value = {"/all", "/all/"})
    public ResponseEntity<Object> getAllData(
            @RequestParam(value = "dateStart") final Timestamp dateStart,
            @RequestParam(value = "dateEnd") final Timestamp dateEnd,
            @RequestParam(value = "sensorId") final Long sensorId) {
        return new ResponseEntity<>(indicatorService.getAllData(dateStart, dateEnd, sensorId), HttpStatus.OK);
    }

    @GetMapping(value = {"/current", "/current/"})
    public ResponseEntity<Object> getCurrentData(
            @RequestParam(value = "buildingId") final Long buildingId) {
        return new ResponseEntity<>(indicatorService.getCurrent(buildingId), HttpStatus.OK);
    }

    @GetMapping(value = {"/average", "/average/"})
    public ResponseEntity<Object> getAverageData() {
        return new ResponseEntity<>(indicatorService.getAllAverage(), HttpStatus.OK);
    }

    @Autowired
    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }
}
