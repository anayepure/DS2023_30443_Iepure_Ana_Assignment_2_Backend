package com.example.dsproject.services;


import com.example.dsproject.dtos.AccountUserDto;
import com.example.dsproject.dtos.DeviceDto;
import com.example.dsproject.dtos.MeasurementDto;
import com.example.dsproject.dtos.builders.AccountUserBuilder;
import com.example.dsproject.dtos.builders.DeviceBuilder;
import com.example.dsproject.dtos.builders.MeasurementBuilder;
import com.example.dsproject.entities.AccountUser;
import com.example.dsproject.entities.Device;
import com.example.dsproject.entities.Measurement;
import com.example.dsproject.repositories.AccountUserRepository;
import com.example.dsproject.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final AccountUserRepository accountUserRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, AccountUserRepository accountUserRepository)
    {
        this.deviceRepository = deviceRepository;
        this.accountUserRepository = accountUserRepository;
    }

    public List<DeviceDto> getAllDeviceDto()
    {
        return deviceRepository.findAll().stream().map(DeviceBuilder::todeviceDto).collect(Collectors.toList());
    }

    public DeviceDto getDeviceByDescription(String description)
    {
        Device device=deviceRepository.findByDescription(description);
        return DeviceBuilder.todeviceDto(device);
    }

    @Transactional
    public void deleteDeviceById(Long id)
    {
        deviceRepository.deleteByDeviceId(id);
    }

    public DeviceDto updateDevice(Long id, DeviceDto deviceDto)
    {
        Device deviceFound=deviceRepository.findByDeviceId(id).orElse(null);
        deviceFound.setLocation(deviceDto.getLocation());
        deviceFound.setDescription(deviceDto.getDescription());
        deviceFound.setMaximumconsumption(deviceDto.getMaximumconsumption());
        return DeviceBuilder.todeviceDto(deviceRepository.save(deviceFound));
    }

    public Long saveDevice(DeviceDto deviceDto)
    {
        Device device=DeviceBuilder.toEntity(deviceDto);
        device=deviceRepository.save(device);
        return device.getDeviceId();
    }

    public List<MeasurementDto> getDeviceMeasurements(Long id)
    {
        Device device=deviceRepository.findByDeviceId(id).orElse(null);
        DeviceDto deviceDto= DeviceBuilder.todeviceDto(device);
        return deviceDto.getMeasurements().stream().toList();
    }

    public DeviceDto getDeviceById(Long id)
    {
        Device device=deviceRepository.findByDeviceId(id).orElse(null);
        return DeviceBuilder.todeviceDto(device);
    }


}
