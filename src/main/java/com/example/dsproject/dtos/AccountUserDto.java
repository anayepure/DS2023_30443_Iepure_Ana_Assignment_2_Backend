package com.example.dsproject.dtos;

import com.example.dsproject.entities.Device;
import com.example.dsproject.entities.Role;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AccountUserDto extends RepresentationModel<AccountUserDto> {

    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private Role role;
    private List<DeviceDto> devices;

    public AccountUserDto(Long id, String username, String password, Role role, List<DeviceDto> devices) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.role=role;
        this.devices=devices;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DeviceDto> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceDto> devices) {
        this.devices = devices;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
