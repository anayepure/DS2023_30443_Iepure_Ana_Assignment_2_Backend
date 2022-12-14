package com.example.dsproject.controllers;

import com.example.dsproject.dtos.AccountUserDto;
import com.example.dsproject.dtos.MeasurementDto;
import com.example.dsproject.entities.Measurement;
import com.example.dsproject.services.MeasurementService;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(value = "/api/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("/byDate/{date}")
    @ResponseBody
    public ResponseEntity<List<MeasurementDto>> getMeasurementsByDate(@PathVariable("date") String date) throws ParseException {
        return ResponseEntity.ok(measurementService.findMeasurementByDate(date));
    }

    @PostMapping("/{deviceId}")
    public ResponseEntity<Long> insertMeasurement(@PathVariable Long deviceId, @RequestBody MeasurementDto measurementDto)
    {
        Long measurementID = measurementService.saveMeasurement(measurementDto, deviceId);
        return new ResponseEntity<>(measurementID, HttpStatus.CREATED);
    }

}
