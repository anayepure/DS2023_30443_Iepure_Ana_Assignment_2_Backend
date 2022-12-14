package com.example.dsproject.repositories;

import com.example.dsproject.entities.AccountUser;
import com.example.dsproject.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    Device findByDescription(String description);

    Optional<Device> findByDeviceId(Long id);

    void deleteByDeviceId(Long id);
}
