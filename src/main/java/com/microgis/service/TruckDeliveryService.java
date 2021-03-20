package com.microgis.service;

import com.microgis.controller.dto.*;
import com.microgis.persistence.entity.DeviceLightweight;
import com.microgis.persistence.entity.Driver;
import com.microgis.persistence.entity.TruckDelivery;
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

    private final DeviceService deviceService;

    private final DriverService driverService;

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
     * Format response with delivery information for driver
     *
     * @param loadNumber delivery number
     * @return delivery information for response
     */
    public DeliveryResponse getDeliveryResponse(Integer loadNumber) {
        var truckDelivery = truckDeliveryRepository.findTruckDeliveryByLoadNumber(loadNumber).orElse(null);
        if (truckDelivery != null) {
            LOGGER.info("Delivery information found for loadNumber - {}", loadNumber);
            DeliveryResponse deliveryResponse = new DeliveryResponse();
            deliveryResponse.setDriverName(truckDelivery.getDriver().getDisplayName());
            formatAddressFromResponse(truckDelivery, deliveryResponse);
            formatAddressToResponse(truckDelivery, deliveryResponse);
            formatAdditionalInformationResponse(truckDelivery, deliveryResponse);
            return deliveryResponse;
        }
        LOGGER.info("Delivery information was not found for loadNumber - {}", loadNumber);
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
        if (truckDelivery.getPallets() != null) {
            deliveryResponse.setPallets(truckDelivery.getPallets());
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
        InfoFrom infoFrom = new InfoFrom();
        infoFrom.setAddressFrom(truckDelivery.getAddressFrom());
        infoFrom.setCityFrom(truckDelivery.getCityFrom());
        infoFrom.setStateFrom(truckDelivery.getStateFrom());
        infoFrom.setTimeFrom(truckDelivery.getTimeFrom().toString());
        infoFrom.setZipcodeFrom(truckDelivery.getZipcodeFrom());
        deliveryResponse.setInfoFrom(infoFrom);
    }

    /**
     * Format address information details where to deliver
     *
     * @param truckDelivery    entity class
     * @param deliveryResponse dto class
     */
    private void formatAddressToResponse(TruckDelivery truckDelivery, DeliveryResponse deliveryResponse) {
        InfoTo infoTo = new InfoTo();
        infoTo.setAddressTo(truckDelivery.getAddressTo());
        infoTo.setCityTo(truckDelivery.getCityTo());
        infoTo.setStateTo(truckDelivery.getStateTo());
        infoTo.setTimeTo(truckDelivery.getTimeTo().toString());
        infoTo.setZipcodeTo(truckDelivery.getZipcodeTo());
        deliveryResponse.setInfoTo(infoTo);
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
        truckDelivery.setAddressFrom(infoFrom.getAddressFrom());
        truckDelivery.setCityFrom(infoFrom.getCityFrom());
        truckDelivery.setStateFrom(infoFrom.getStateFrom());
        try {
            truckDelivery.setTimeFrom(new Timestamp(dateFormat.parse(infoFrom.getTimeFrom()).getTime()));
        } catch (ParseException e) {
            LOGGER.error("Could not parse date - {}", infoFrom.getTimeFrom());
        }
        truckDelivery.setZipcodeFrom(infoFrom.getZipcodeFrom());
    }

    /**
     * Set information to entity
     *
     * @param infoTo        information about where to deliver cargo
     * @param truckDelivery entity class
     */
    private void saveInformationTo(InfoTo infoTo, TruckDelivery truckDelivery) {
        truckDelivery.setAddressTo(infoTo.getAddressTo());
        truckDelivery.setCityTo(infoTo.getCityTo());
        truckDelivery.setStateTo(infoTo.getStateTo());
        try {
            truckDelivery.setTimeTo(new Timestamp(dateFormat.parse(infoTo.getTimeTo()).getTime()));
        } catch (ParseException e) {
            LOGGER.error("Could not parse date - {}", infoTo.getTimeTo());
        }
        truckDelivery.setZipcodeTo(infoTo.getZipcodeTo());
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
        var driver = driverService.findDriverByDisplayNameAndContactPhone(driverInfo.getName(), driverInfo.getPhone());
        if (driver == null) {
            LOGGER.info("Driver wasn't found driverName - {}", driverInfo.getName());
            Driver driverEntity = new Driver();
            driverEntity.setDisplayName(driverInfo.getName());
            driverEntity.setContactPhone(driverInfo.getPhone());
            driverEntity.setMobile(true);
            if (driverInfo.getEmail() != null) {
                driverEntity.setContactEmail(driverInfo.getEmail());
            }
            driverService.save(driverEntity);
            return driverEntity;
        }
        LOGGER.info("Driver found deviceId - {}", driver.getId());
        return driver;
    }

}