package com.microgis.persistence.repository;

import com.microgis.persistence.entity.DeviceLightweight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<DeviceLightweight, Integer> {

    Optional<DeviceLightweight> findDeviceLightweightByLicensePlate(@Param("licensePlate") String licensePlate);

}