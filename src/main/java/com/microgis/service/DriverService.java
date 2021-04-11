package com.microgis.service;

import com.microgis.persistence.entity.Driver;
import com.microgis.persistence.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public Driver findDriverByContactPhone( String driverPhoneNumber){
        return driverRepository.findDriverByContactPhone( driverPhoneNumber).orElse(null);
    }

    public void save(Driver driver){
        driverRepository.save(driver);
    }

}