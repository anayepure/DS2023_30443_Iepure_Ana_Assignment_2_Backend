package com.example.dsproject.controllers;

import com.example.dsproject.dtos.AccountUserDto;
import com.example.dsproject.dtos.DeviceDto;
import com.example.dsproject.entities.AccountUser;
import com.example.dsproject.services.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value="/api/users")
public class AccountUserController {

    private final AccountUserService accountUserService;

    @Autowired
    public AccountUserController(AccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @GetMapping("/login/{username}/{password}")
    @ResponseBody
    public ResponseEntity<AccountUserDto> login(@PathVariable String username, @PathVariable String password)
    {
        AccountUserDto accountUserDtoFound=accountUserService.logInUser(username, password);
        return new ResponseEntity<>(accountUserDtoFound, HttpStatus.OK);
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<AccountUserDto>> getAllUsers()
    {
        return ResponseEntity.ok(accountUserService.getAllAccountUsersDto());
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteAccountUser(@PathVariable Long id)
    {
        accountUserService.deleteUserById(id);
    }

    @PutMapping("update/{id}")
    @ResponseBody
    public ResponseEntity<AccountUserDto> updateAccoutUser(@PathVariable Long id, @RequestBody AccountUserDto accountUserDto)
    {
        return ResponseEntity.ok(accountUserService.updateAccountUser(id, accountUserDto));
    }

    @PostMapping()
    public ResponseEntity<Long> insertUser(@Valid @RequestBody AccountUserDto accountUserDto) {
        Long userID = accountUserService.saveAccountUser(accountUserDto);
        return new ResponseEntity<>(userID, HttpStatus.CREATED);
    }

    @GetMapping("add-device/{deviceId}/{userId}")
    @ResponseBody
    public ResponseEntity<AccountUserDto> linkDeviceToUser(@PathVariable Long deviceId, @PathVariable Long userId)
    {
        return ResponseEntity.ok(accountUserService.linkDeviceToUser(deviceId,userId));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AccountUserDto> getUserById(@PathVariable Long id)
    {
        return ResponseEntity.ok(accountUserService.getUserById(id));
    }

    @GetMapping("devices/{id}")
    @ResponseBody
    public ResponseEntity<List<DeviceDto>> getUserDevices(@PathVariable Long id)
    {
        return ResponseEntity.ok(accountUserService.getUserDevices(id));
    }



}
