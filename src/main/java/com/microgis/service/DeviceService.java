package com.microgis.service;

import com.microgis.persistence.entity.DeviceLightweight;
import com.microgis.persistence.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"device"})
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Cacheable(key = "#root.methodName + #id", unless = "#result == null")
    public DeviceLightweight findById(int id) {
        return deviceRepository.findById(id).orElse(null);
    }

    public DeviceLightweight findDeviceLightweightByLicensePlate(String licensePlate) {
        return deviceRepository.findDeviceLightweightByLicensePlate(licensePlate).orElse(null);
    }

    public void save(DeviceLightweight deviceLightweight) {
        deviceRepository.save(deviceLightweight);
    }

}