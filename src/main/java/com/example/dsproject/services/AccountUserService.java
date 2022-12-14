package com.example.dsproject.services;

import com.example.dsproject.dtos.AccountUserDto;
import com.example.dsproject.dtos.DeviceDto;
import com.example.dsproject.dtos.builders.AccountUserBuilder;
import com.example.dsproject.entities.AccountUser;
import com.example.dsproject.entities.Device;
import com.example.dsproject.repositories.AccountUserRepository;
import com.example.dsproject.repositories.DeviceRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountUserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountUserService.class);

    private final AccountUserRepository accountUserRepository;
    private final DeviceRepository deviceRepository;


    public List<AccountUserDto> getAllAccountUsersDto()
    {
        return accountUserRepository.findAll().stream().map(AccountUserBuilder::toAccountUserDto).collect(Collectors.toList());
    }


   public AccountUserDto logInUser(String username, String password) {
        Optional<AccountUser> accountUserFound= accountUserRepository.findByUsername(username);
       if (accountUserFound.isEmpty()) {
           LOGGER.error("Person with id {} was not found in db", username);
           throw new ResourceNotFoundException(AccountUser.class.getSimpleName());
       }
       if (password.equals(accountUserFound.get().getPassword()))

       {
           return AccountUserBuilder.toAccountUserDto(accountUserFound.get());
       }
       return null;
    }

    @Transactional
    public void deleteUserById(Long id)
    {
        accountUserRepository.deleteByUserId(id);
    }

    public AccountUserDto updateAccountUser(Long id, AccountUserDto accountUserDto)
    {
        AccountUser accountUserFound=accountUserRepository.findByUserId(id).get();
        accountUserFound.setUsername(accountUserDto.getUsername());
        accountUserFound.setPassword(accountUserDto.getPassword());
        return AccountUserBuilder.toAccountUserDto(accountUserRepository.save(accountUserFound));
    }

    public Long saveAccountUser(AccountUserDto accountUserDto)
    {
        AccountUser accountUser=AccountUserBuilder.toEntity(accountUserDto);
        accountUser=accountUserRepository.save(accountUser);
        return accountUser.getUserId();
    }

    public AccountUserDto linkDeviceToUser(Long deviceId, Long userId)
    {
        Device deviceFound=deviceRepository.findByDeviceId(deviceId).orElse(null);
        AccountUser accountUserFound=accountUserRepository.findByUserId(userId).orElse(null);
        assert accountUserFound != null;
        List<Device> devices=accountUserFound.getDevices();
        devices.add(deviceFound);
        accountUserFound.setDevices(devices);
        deviceFound.setAccountuser(accountUserFound);
        return AccountUserBuilder.toAccountUserDto(accountUserFound);

    }

    public AccountUserDto getUserById(Long id)
    {
        AccountUser accountUser=accountUserRepository.findByUserId(id).orElse(null);
        return AccountUserBuilder.toAccountUserDto(accountUser);
    }

    public List<DeviceDto> getUserDevices(Long id)
    {
        AccountUser accountUser=accountUserRepository.findByUserId(id).orElse(null);
        AccountUserDto accountUserDto=AccountUserBuilder.toAccountUserDto(accountUser);
        assert accountUserDto != null;
        return accountUserDto.getDevices().stream().toList();
    }





}
