package com.microgis.service;

import com.microgis.controller.dto.*;
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
    public void saveOrUpdateDeliveryInformation(TruckDeliveryInfo truckDeliveryInfo) {
        LOGGER.info("Saving delivery information into database");
        var truckDelivery = truckDeliveryRepository
                .findTruckDeliveryByLoadNumber(truckDeliveryInfo.getLoadNumber())
                .orElse(new TruckDelivery());
        truckDelivery.setLoadNumber(truckDeliveryInfo.getLoadNumber());
        truckDelivery.setTrailerNumber(truckDeliveryInfo.getTruck().getTrailerNumber());
        truckDelivery.setTrailerPlateNumber(truckDeliveryInfo.getTruck().getTrailerPlateNumber());
        truckDelivery.setStatus(truckDeliveryInfo.getStatus());
        var deviceLightweight = saveDeviceInformation(truckDeliveryInfo.getTruck());
        var driver = saveDriverInformation(truckDeliveryInfo.getDriver());
        truckDelivery.setTruck(deviceLightweight);
        truckDelivery.setDriver(driver);
        saveAdditionalInformation(truckDeliveryInfo, truckDelivery);
        saveBrokerInfo(truckDeliveryInfo.getBroker(), truckDelivery);
        saveInformationFrom(truckDeliveryInfo.getInformationFrom(), truckDelivery);
        saveInformationTo(truckDeliveryInfo.getInformationTo(), truckDelivery);
        truckDeliveryRepository.save(truckDelivery);
    }

    /**
     * Set additional information to entity
     *
     * @param truckDeliveryInfo delivery information
     * @param truckDelivery     entity class
     */
    private void saveAdditionalInformation(TruckDeliveryInfo truckDeliveryInfo, TruckDelivery truckDelivery) {
        truckDelivery.setCargoType(truckDeliveryInfo.getCargoType());
        truckDelivery.setPallets(truckDeliveryInfo.getPallets());
        truckDelivery.setWeight(truckDeliveryInfo.getWeight());
    }

    /**
     * Set information to entity
     *
     * @param infoFrom      information about where from deliver cargo
     * @param truckDelivery entity class
     */
    private void saveInformationFrom(InfoFrom infoFrom, TruckDelivery truckDelivery) {
        truckDelivery.setAddressLineFrom(infoFrom.getAddressLineFrom());
        truckDelivery.setCityFrom(infoFrom.getCityFrom());
        truckDelivery.setStateFrom(infoFrom.getStateFrom());
        truckDelivery.setTimeFrom(infoFrom.getTimeFrom());
    }

    /**
     * Set information to entity
     *
     * @param infoTo        information about where to deliver cargo
     * @param truckDelivery entity class
     */
    private void saveInformationTo(InfoTo infoTo, TruckDelivery truckDelivery) {
        truckDelivery.setAddressLineTo(infoTo.getAddressLineTo());
        truckDelivery.setCityTo(infoTo.getCityTo());
        truckDelivery.setStateTo(infoTo.getStateTo());
        truckDelivery.setTimeTo(infoTo.getTimeTo());
    }

    /**
     * Set broker information to entity
     *
     * @param broker        broker information
     * @param truckDelivery entity class
     */
    private void saveBrokerInfo(BrokerInfo broker, TruckDelivery truckDelivery) {
        truckDelivery.setBrokerAddress(broker.getBrokerAddress());
        truckDelivery.setBrokerName(broker.getBrokerName());
        truckDelivery.setBrokerCompany(broker.getBrokerCompany());
        truckDelivery.setBrokerPhone(broker.getBrokerPhone());
        if (broker.getBrokerPhoneExtension() != null) {
            truckDelivery.setBrokerPhoneExtension(broker.getBrokerPhoneExtension());
        }
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
            LOGGER.info("Device wasn't found licensePlate - {} and truckNumber - {}", truckInfo.getPlateNumber(),
                    truckInfo.getTruckNumber());
            DeviceLightweight deviceLightweight = new DeviceLightweight();
            deviceLightweight.setLicensePlate(truckInfo.getPlateNumber());
            deviceLightweight.setLegacyDeviceId(truckInfo.getTruckNumber());
            deviceLightweight.setEquipmentType("Mobile");
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
        var driver = driverRepository.findDriverByDisplayNameAndContactPhone(driverInfo.getName(), driverInfo.getPhone());
        if (driver.isEmpty()) {
            LOGGER.info("Driver wasn't found driverName - {}", driverInfo.getName());
            Driver driverEntity = new Driver();
            driverEntity.setDisplayName(driverInfo.getName());
            driverEntity.setContactPhone(driverInfo.getPhone());
            driverEntity.setMobile(true);
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