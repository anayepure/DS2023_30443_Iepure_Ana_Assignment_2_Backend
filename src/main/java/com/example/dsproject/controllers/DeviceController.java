package com.example.dsproject.controllers;

import com.example.dsproject.dtos.AccountUserDto;
import com.example.dsproject.dtos.DeviceDto;
import com.example.dsproject.dtos.MeasurementDto;
import com.example.dsproject.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<DeviceDto>> getAllDevices()
    {
        return ResponseEntity.ok(deviceService.getAllDeviceDto());
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteDevice(@PathVariable Long id)
    {
        deviceService.deleteDeviceById(id);
    }

    @PutMapping("update/{id}")
    @ResponseBody
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable Long id, @RequestBody DeviceDto deviceDto)
    {
        return ResponseEntity.ok(deviceService.updateDevice(id, deviceDto));
    }

    @PostMapping()
    public ResponseEntity<Long> insertDevice(@Valid @RequestBody DeviceDto deviceDto) {
        Long deviceID = deviceService.saveDevice(deviceDto);
        return new ResponseEntity<>(deviceID, HttpStatus.CREATED);
    }
    @GetMapping("measurements/{id}")
    @ResponseBody
    public ResponseEntity<List<MeasurementDto>> geDeviceMeasurements(@PathVariable Long id)
    {
        return ResponseEntity.ok(deviceService.getDeviceMeasurements(id));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable Long id)
    {
        return ResponseEntity.ok(deviceService.getDeviceById(id));
    }



}
