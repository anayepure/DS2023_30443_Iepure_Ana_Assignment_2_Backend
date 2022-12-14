package com.example.dsproject.dtos.builders;

import com.example.dsproject.dtos.DeviceDto;
import com.example.dsproject.dtos.MeasurementDto;
import com.example.dsproject.entities.Device;
import com.example.dsproject.entities.Measurement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeviceBuilder {
    public static List<MeasurementDto> convertEntityToDto(List<Measurement> measurements) {
        List<MeasurementDto> newMeasurementDtoList = new ArrayList<>();

        for (Measurement measurement : measurements) {
            MeasurementDto measurmentDto = MeasurementBuilder.toMeasurementDto(measurement);

            newMeasurementDtoList.add(measurmentDto);
        }

        return newMeasurementDtoList;
    }
    public static DeviceDto todeviceDto(Device device)
    {
      return new DeviceDto(device.getDeviceId(), device.getLocation(), device.getDescription(), device.getMaximumconsumption(), convertEntityToDto(device.getMeasurements()));
    }

    public static Device toEntity(DeviceDto deviceDto)
    {
        return new Device(deviceDto.getLocation(), deviceDto.getDescription(), deviceDto.getMaximumconsumption());
    }
}
