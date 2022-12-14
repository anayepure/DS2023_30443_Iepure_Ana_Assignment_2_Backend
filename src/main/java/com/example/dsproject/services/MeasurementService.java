package com.example.dsproject.services;

import com.example.dsproject.dtos.MeasurementDto;
import com.example.dsproject.dtos.builders.MeasurementBuilder;
import com.example.dsproject.entities.Device;
import com.example.dsproject.entities.Measurement;
import com.example.dsproject.repositories.DeviceRepository;
import com.example.dsproject.repositories.MeasurementRepository;
import com.example.dsproject.websocket.WebSocketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    private final DeviceRepository deviceRepository;
    private final MeasurementRepository measurementRepository;

    @Autowired
    private WebSocketController webSocketController;

    public MeasurementService(DeviceRepository deviceRepository, MeasurementRepository measurementRepository) {
        this.deviceRepository = deviceRepository;
        this.measurementRepository = measurementRepository;
    }

    public List<MeasurementDto> findMeasurementByDate(String date) throws ParseException {
        return measurementRepository.findAllByMeasurementDate(
                new SimpleDateFormat("yyyy-MM-dd").parse(date)).stream()
            .map(MeasurementBuilder::toMeasurementDto).collect(Collectors.toList());
    }
    public Long saveMeasurement(MeasurementDto measurementDto, Long deviceId) {
        Measurement measurement= MeasurementBuilder.toEntity(measurementDto);
        Device deviceFound=deviceRepository.findByDeviceId(deviceId).orElse(null);
        measurement.setDevice(deviceFound);
        if (measurement.getAmount()>deviceFound.getMaximumconsumption())
        {
            Double difference = measurement.getAmount()-deviceFound.getMaximumconsumption();
            webSocketController.onReceivedMessage(deviceFound.getDescription() + " " + "excedeed maximum value by" + " " + difference);
        }
        measurement=measurementRepository.save(measurement);
        return measurement.getMeasurementId();
    }
}
