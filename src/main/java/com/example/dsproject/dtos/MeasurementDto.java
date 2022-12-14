package com.example.dsproject.dtos;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class MeasurementDto extends RepresentationModel<MeasurementDto> {

    Long id;
    @NotNull
    private Double amount;
    @NotNull
    private Date measurementDate;

    public MeasurementDto(Double amount, Date measurementDate) {
        this.amount = amount;
        this.measurementDate = measurementDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }


}
