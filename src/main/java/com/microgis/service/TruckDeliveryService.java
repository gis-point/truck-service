package com.microgis.service;

import com.microgis.controller.dto.DriverInfo;
import com.microgis.controller.dto.TruckDeliveryInfo;
import com.microgis.controller.dto.TruckInfo;
import com.microgis.persistence.entity.DeviceLightweight;
import com.microgis.persistence.entity.Driver;
import com.microgis.persistence.entity.TruckDelivery;
import com.microgis.persistence.repository.DriverRepository;
import com.microgis.persistence.repository.TruckDeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TruckDeliveryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TruckDeliveryService.class);

    private final TruckDeliveryRepository truckDeliveryRepository;

    private final DeviceService deviceService;

    private final DriverRepository driverRepository;

    /**
     * Saving delivery information into database
     *
     * @param truckDeliveryInfo delivery information
     */
    public void saveDeliveryInformation(TruckDeliveryInfo truckDeliveryInfo) {
        LOGGER.info("Saving delivery information into database");
        var truckDelivery = truckDeliveryRepository
                .findTruckDeliveryByLoadNumber(truckDeliveryInfo.getLoadNumber()).orElse(new TruckDelivery());
        truckDelivery.setLoadNumber(truckDeliveryInfo.getLoadNumber());
        truckDelivery.setTrailerNumber(truckDeliveryInfo.getTrailerNumber());
        truckDelivery.setAddressLineFrom(truckDeliveryInfo.getAddressLineFrom());
        truckDelivery.setCityFrom(truckDeliveryInfo.getCityFrom());
        truckDelivery.setStateFrom(truckDeliveryInfo.getStateFrom());
        truckDelivery.setTimeFrom(truckDeliveryInfo.getTimeFrom());
        truckDelivery.setAddressLineTo(truckDeliveryInfo.getAddressLineTo());
        truckDelivery.setCityTo(truckDeliveryInfo.getCityTo());
        truckDelivery.setStateTo(truckDeliveryInfo.getStateTo());
        truckDelivery.setTimeTo(truckDeliveryInfo.getTimeTo());
        truckDelivery.setStatus(truckDeliveryInfo.getStatus());
        var deviceLightweight = saveDeviceInformation(truckDeliveryInfo.getTruck());
        var driver = saveDriverInformation(truckDeliveryInfo.getDriver());
        truckDeliveryRepository.save(truckDelivery);
    }

    /**
     * Checking if device exist if not add it to database
     *
     * @param truckInfo truck information
     * @return device entity
     */
    private DeviceLightweight saveDeviceInformation(TruckInfo truckInfo) {
        var device = deviceService.findDeviceLightweightByLicensePlate(truckInfo.getPlateNumber());
        if (device == null) {
            LOGGER.info("Device wasn't found licensePlate - {} and truckNumber - {}", truckInfo.getPlateNumber(), truckInfo.getTruckNumber());
            DeviceLightweight deviceLightweight = new DeviceLightweight();
            deviceLightweight.setLicensePlate(truckInfo.getPlateNumber());
            deviceLightweight.setLegacyDeviceId(truckInfo.getTruckNumber());
            deviceService.save(deviceLightweight);
            return deviceLightweight;
        }
        LOGGER.info("Device found deviceId - {}", device.getId());
        return device;
    }

    /**
     * Checking if driver exist if not add it to database
     *
     * @param driverInfo driver information
     * @return driver entity
     */
    private Driver saveDriverInformation(DriverInfo driverInfo) {
        var driver = driverRepository.findDriverByDisplayName(driverInfo.getName());
        if (driver.isEmpty()) {
            LOGGER.info("Driver wasn't found driverName - {}", driverInfo.getName());
            Driver driverEntity = new Driver();
            driverEntity.setDisplayName(driverInfo.getName());
            if (driverInfo.getPhone() != null) {
                driverEntity.setContactPhone(driverInfo.getPhone());
            }
            if (driverInfo.getEmail() != null) {
                driverEntity.setContactEmail(driverInfo.getEmail());
            }
            driverRepository.save(driverEntity);
            return driverEntity;
        }
        LOGGER.info("Driver found deviceId - {}", driver.get().getId());
        return driver.get();
    }

}