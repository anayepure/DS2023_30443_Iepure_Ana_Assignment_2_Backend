package com.example.dsproject.dtos.builders;

import com.example.dsproject.dtos.AccountUserDto;
import com.example.dsproject.dtos.DeviceDto;
import com.example.dsproject.entities.AccountUser;
import com.example.dsproject.entities.Device;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class AccountUserBuilder {

    public static List<DeviceDto> convertEntityToDto(List<Device> devices) {
        List<DeviceDto> newDeviceDtoList = new ArrayList<>();

        for (Device device : devices) {
            DeviceDto deviceDto = DeviceBuilder.todeviceDto(device);

            newDeviceDtoList.add(deviceDto);
        }

        return newDeviceDtoList;
    }
    public static AccountUserDto toAccountUserDto(AccountUser accountUser)
    {
        return (accountUser==null) ? null :  new AccountUserDto(accountUser.getUserId(),accountUser.getPassword(), accountUser.getUsername(), accountUser.getRole(), convertEntityToDto(accountUser.getDevices()));
    }

    public static AccountUser toEntity(AccountUserDto accountUserDto)
    {
        return new AccountUser(accountUserDto.getUsername(), accountUserDto.getPassword(),accountUserDto.getRole());
    }


}
