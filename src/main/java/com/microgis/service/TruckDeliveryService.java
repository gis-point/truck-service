package com.microgis.service;

import com.microgis.config.AppConfig;
import com.microgis.controller.dto.*;
import com.microgis.persistence.dto.DeliveryStatus;
import com.microgis.persistence.entity.AccountLightweight;
import com.microgis.persistence.entity.DeviceLightweight;
import com.microgis.persistence.entity.Driver;
import com.microgis.persistence.entity.TruckDelivery;
import com.microgis.persistence.repository.AccountRepository;
import com.microgis.persistence.repository.TruckDeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class TruckDeliveryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TruckDeliveryService.class);

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private final TruckDeliveryRepository truckDeliveryRepository;

    private final AccountRepository accountRepository;

    private final DeviceService deviceService;

    private final DriverService driverService;

    private final AppConfig appConfig;

    /**
     * Saving delivery information into database
     *
     * @param truckDeliveryInfo delivery information
     */
    public void saveOrUpdateDeliveryInformation(TruckDeliveryInfo truckDeliveryInfo) {
        LOGGER.info("Saving delivery information into database");
        var truckDelivery = truckDeliveryRepository.findTruckDeliveryByLoadNumber(truckDeliveryInfo.getLoadNumber())
                .orElse(new TruckDelivery());
        truckDelivery.setDomain(truckDeliveryInfo.getAccountDomain());
        truckDelivery.setTrailerNumber(truckDeliveryInfo.getTruck().getTrailerNumber());
        truckDelivery.setTrailerPlateNumber(truckDeliveryInfo.getTruck().getTrailerPlateNumber());
        truckDelivery.setStatus(DeliveryStatus.getDeliveryStatus(truckDeliveryInfo.getStatus()));
        cleanCache(truckDeliveryInfo, truckDelivery);
        var deviceLightweight = saveDeviceInformation(truckDeliveryInfo.getTruck());
        var driver = saveDriverInformation(truckDeliveryInfo.getDriver());
        truckDelivery.setTruck(deviceLightweight);
        truckDelivery.setDriver(driver);
        saveAdditionalInformation(truckDeliveryInfo, truckDelivery);
        saveBrokerInfo(truckDeliveryInfo.getBroker(), truckDelivery);
        saveInformationFrom(truckDeliveryInfo.getAddressFrom(), truckDelivery);
        saveInformationTo(truckDeliveryInfo.getAddressTo(), truckDelivery);
        truckDeliveryRepository.save(truckDelivery);
    }

    /**
     * Format response with delivery information for driver
     *
     * @param username driver name
     * @return delivery information for response
     */
    public DeliveryResponse getDeliveryResponse(String username) {
        var truckDelivery = truckDeliveryRepository.findTruckDeliveryByLoginAndStatus(username, DeliveryStatus.CREATED)
                .orElse(null);
        if (truckDelivery != null) {
            LOGGER.info("Delivery information found for user - {}", username);
            DeliveryResponse deliveryResponse = new DeliveryResponse();
            deliveryResponse.setDeviceId(truckDelivery.getTruck().getId());
            deliveryResponse.setDriverName(truckDelivery.getDriver().getDisplayName());
            formatAddressFromResponse(truckDelivery, deliveryResponse);
            formatAddressToResponse(truckDelivery, deliveryResponse);
            formatAdditionalInformationResponse(truckDelivery, deliveryResponse);
            return deliveryResponse;
        }
        LOGGER.info("Delivery information was not found for user - {}", username);
        return null;
    }

    /**
     * Format additional information for driver response
     *
     * @param truckDelivery    entity class
     * @param deliveryResponse dto class
     */
    private void formatAdditionalInformationResponse(TruckDelivery truckDelivery, DeliveryResponse deliveryResponse) {
        if (truckDelivery.getCargoType() != null) {
            deliveryResponse.setCargoType(truckDelivery.getCargoType());
        }
        if (truckDelivery.getWeight() != null) {
            deliveryResponse.setWeight(truckDelivery.getWeight());
        }
    }

    /**
     * Format address information where from deliver
     *
     * @param truckDelivery    entity class
     * @param deliveryResponse dto class
     */
    private void formatAddressFromResponse(TruckDelivery truckDelivery, DeliveryResponse deliveryResponse) {
        AddressLine addressFrom = new AddressLine();
        addressFrom.setAddress(truckDelivery.getAddressFrom());
        addressFrom.setCity(truckDelivery.getCityFrom());
        addressFrom.setState(truckDelivery.getStateFrom());
        addressFrom.setTime(truckDelivery.getTimeFrom().toString());
        addressFrom.setZipcode(truckDelivery.getZipcodeFrom());
        addressFrom.setCompany(truckDelivery.getCompanyFrom());
        deliveryResponse.setAddressLineFrom(addressFrom);
    }

    /**
     * Format address information details where to deliver
     *
     * @param truckDelivery    entity class
     * @param deliveryResponse dto class
     */
    private void formatAddressToResponse(TruckDelivery truckDelivery, DeliveryResponse deliveryResponse) {
        AddressLine addressTo = new AddressLine();
        addressTo.setAddress(truckDelivery.getAddressTo());
        addressTo.setCity(truckDelivery.getCityTo());
        addressTo.setState(truckDelivery.getStateTo());
        addressTo.setTime(truckDelivery.getTimeTo().toString());
        addressTo.setZipcode(truckDelivery.getZipcodeTo());
        addressTo.setCompany(truckDelivery.getCompanyTo());
        deliveryResponse.setAddressLineTo(addressTo);
    }

    /**
     * Set additional information to entity
     *
     * @param truckDeliveryInfo delivery information
     * @param truckDelivery     entity class
     */
    private void saveAdditionalInformation(TruckDeliveryInfo truckDeliveryInfo, TruckDelivery truckDelivery) {
        truckDelivery.setLoadNumber(truckDeliveryInfo.getLoadNumber());
        truckDelivery.setPickupNumber(truckDeliveryInfo.getPickupNumber());
        truckDelivery.setCargoType(truckDeliveryInfo.getCargoType());
        truckDelivery.setPallets(truckDeliveryInfo.getPallets());
        truckDelivery.setWeight(truckDeliveryInfo.getWeight());
    }

    /**
     * Set information to entity
     *
     * @param addressFrom information about where from deliver cargo
     * @param truckDelivery   entity class
     */
    private void saveInformationFrom(AddressLine addressFrom, TruckDelivery truckDelivery) {
        truckDelivery.setAddressFrom(addressFrom.getAddress());
        truckDelivery.setCityFrom(addressFrom.getCity());
        truckDelivery.setStateFrom(addressFrom.getState());
        truckDelivery.setCompanyFrom(addressFrom.getCompany());
        try {
            truckDelivery.setTimeFrom(new Timestamp(dateFormat.parse(addressFrom.getTime()).getTime()));
        } catch (ParseException e) {
            LOGGER.error("Could not parse date - {}", addressFrom.getTime());
        }
        truckDelivery.setZipcodeFrom(addressFrom.getZipcode());
    }

    /**
     * Set information to entity
     *
     * @param addressTo information about where to deliver cargo
     * @param truckDelivery entity class
     */
    private void saveInformationTo(AddressLine addressTo, TruckDelivery truckDelivery) {
        truckDelivery.setAddressTo(addressTo.getAddress());
        truckDelivery.setCityTo(addressTo.getCity());
        truckDelivery.setStateTo(addressTo.getState());
        truckDelivery.setCompanyTo(addressTo.getCompany());
        try {
            truckDelivery.setTimeTo(new Timestamp(dateFormat.parse(addressTo.getTime()).getTime()));
        } catch (ParseException e) {
            LOGGER.error("Could not parse date - {}", addressTo.getTime());
        }
        truckDelivery.setZipcodeTo(addressTo.getZipcode());
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
        truckDelivery.setBrokerPhoneExtension(broker.getBrokerPhoneExtension());
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
            var account = getAccount();
            deviceLightweight.setAccount(account);
            deviceLightweight.setAccountCode(account.getContactEmail());
            deviceLightweight.setLicensePlate(truckInfo.getPlateNumber());
            deviceLightweight.setLegacyDeviceId(String.valueOf(truckInfo.getTruckNumber()));
            deviceLightweight.setEquipmentType("Mobile");
            deviceLightweight.setRecordEvent(true);
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
        var driver = driverService.findDriverByContactPhone(driverInfo.getPhone());
        if (driver == null) {
            LOGGER.info("Driver wasn't found driverName - {}", driverInfo.getName());
            Driver driverEntity = new Driver();
            driverEntity.setAccount(getAccount());
            driverEntity.setMobile(true);
            driverEntity.setDisplayName(driverInfo.getName());
            driverEntity.setContactPhone(driverInfo.getPhone());
            driverEntity.setDriverCode("TRUCK_DRIVER");
            if (driverInfo.getEmail() != null) {
                driverEntity.setContactEmail(driverInfo.getEmail());
            }
            driverService.save(driverEntity);
            return driverEntity;
        }
        LOGGER.info("Driver found driverId - {}", driver.getId());
        return driver;
    }

    /**
     * Checking if trip is done and cleaning cache
     *
     * @param truckDeliveryInfo delivery information
     * @param truckDelivery     entity class
     */
    private void cleanCache(TruckDeliveryInfo truckDeliveryInfo, TruckDelivery truckDelivery) {
        if (truckDelivery.getTruck() != null
                && DeliveryStatus.getDeliveryStatus(truckDeliveryInfo.getStatus()).equals(DeliveryStatus.COMPLETED)
                || DeliveryStatus.getDeliveryStatus(truckDeliveryInfo.getStatus()).equals(DeliveryStatus.DELETED)) {
            appConfig.evictCache("device", "findById" + truckDelivery.getTruck().getId());
        }
    }

    private AccountLightweight getAccount() {
        return accountRepository.getOne(1);
    }
}