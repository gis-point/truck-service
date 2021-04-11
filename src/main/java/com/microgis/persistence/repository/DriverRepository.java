package com.microgis.persistence.repository;

import com.microgis.persistence.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    Optional<Driver> findDriverByContactPhone(@Param("contactPhone") String contactPhone);

}