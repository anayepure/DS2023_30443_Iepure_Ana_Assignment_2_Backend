package com.example.dsproject.repositories;

import com.example.dsproject.entities.AccountUser;
import com.example.dsproject.entities.Device;
import com.example.dsproject.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    @Query(value = "SELECT * FROM measurement m WHERE DATE(m.measurement_date) = :measurementDate",
            nativeQuery = true)
    List<Measurement> findAllByMeasurementDate(@Param("measurementDate") Date measurementDate);

}
