package com.example.dsproject.dtos.builders;

import com.example.dsproject.dtos.MeasurementDto;
import com.example.dsproject.entities.Measurement;

public class MeasurementBuilder {
    public static MeasurementDto toMeasurementDto(Measurement measurement)
    {
        return new MeasurementDto(measurement.getAmount(), measurement.getMeasurementDate());
    }

    public static Measurement toEntity(MeasurementDto measurementDto)
    {
        return new Measurement(measurementDto.getAmount(),  measurementDto.getMeasurementDate());
    }
}
