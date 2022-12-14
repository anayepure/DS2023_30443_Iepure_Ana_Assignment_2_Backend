package com.example.dsproject.dtos;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DeviceDto extends RepresentationModel<DeviceDto> {

    Long id;
    @NotEmpty
    private String location;
    @NotEmpty
    @Size(max=100, message="Maximum location length: 100 characters")
    private String description;
    @NotNull
    private Double maximumconsumption;
    private List<MeasurementDto> measurements;


    public DeviceDto(Long id,String location, String description, Double maximumconsumption , List<MeasurementDto> measurements) {
        this.id=id;
        this.location = location;
        this.description = description;
        this.maximumconsumption = maximumconsumption;
        this.measurements=measurements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMaximumconsumption() {
        return maximumconsumption;
    }

    public void setMaximumconsumption(Double maximumconsumption) {
        this.maximumconsumption = maximumconsumption;
    }


    public List<MeasurementDto> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDto> measurements) {
        this.measurements = measurements;
    }


}
