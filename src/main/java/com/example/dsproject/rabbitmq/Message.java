package com.example.dsproject.rabbitmq;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Message {
    private Date timestamp;
    private Long device_id;
    private Double measurement_value;

    public Message(Long device_id, Double measurement_value) {
        this.timestamp = new Date();
        this.device_id = device_id;
        this.measurement_value = measurement_value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }

    public Double getMeasurement_value() {
        return measurement_value;
    }

    public void setMeasurement_value(Double measurement_value) {
        this.measurement_value = measurement_value;
    }
}
