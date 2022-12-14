package com.example.dsproject.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Device implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "deviceid")
    private Long deviceId;


    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "maximumconsumption", nullable = false)
    private Double maximumconsumption;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private AccountUser accountuser;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="device",cascade = CascadeType.ALL)
    private List<Measurement> measurements= new ArrayList<>();


    public Device(String location, String description, Double maximumconsumption) {
        this.location = location;
        this.description = description;
        this.maximumconsumption = maximumconsumption;
    }

    public Device() {

    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public AccountUser getAccountuser() {
        return accountuser;
    }

    public void setAccountuser(AccountUser accountuser) {
        this.accountuser = accountuser;
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

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
