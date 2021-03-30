package com.microgis.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microgis.controller.dto.DeliveryResponse;
import com.microgis.controller.dto.TruckDeliveryInfo;
import com.microgis.persistence.dto.DeliveryStatus;
import com.microgis.persistence.entity.AccountLightweight;
import com.microgis.persistence.entity.DeviceLightweight;
import com.microgis.persistence.entity.Driver;
import com.microgis.persistence.entity.TruckDelivery;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

public class TruckServiceTestFixtures {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    public static TruckDeliveryInfo createTruckDeliveryInfo() {
        return readValue("src/test/resources/response/delivery.json", TruckDeliveryInfo.class);
    }

    public static DeliveryResponse createDeliveryResponse() {
        return readValue("src/test/resources/response/deliveryResponse.json", DeliveryResponse.class);
    }

    public static AccountLightweight createAccountLightweight() {
        var accountLightweight = new AccountLightweight();
        accountLightweight.setId(1);
        accountLightweight.setCode("any@mail.com");
        accountLightweight.setMedicalExaminationTime(2);
        accountLightweight.setVoyageServiceTime(2);
        return accountLightweight;
    }

    public static TruckDelivery createTruckDelivery() {
        var truckDelivery = new TruckDelivery();
        truckDelivery.setAddressFrom("1051 AMBOY AVE");
        truckDelivery.setAddressTo("203 WEST AVE");
        truckDelivery.setBrokerCompany("U.S. FOODSERVICE-PERTH");
        truckDelivery.setBrokerAddress("1051 AMBOY AVE");
        truckDelivery.setBrokerName("ivan");
        truckDelivery.setBrokerPhone("+380 91 934 7200");
        truckDelivery.setBrokerPhoneExtension(34);
        truckDelivery.setCargoType("bricks");
        truckDelivery.setCityFrom("Perth Amboy");
        truckDelivery.setCityTo("Ludlow");
        truckDelivery.setCompanyFrom("company");
        truckDelivery.setCompanyTo("company");
        truckDelivery.setDriver(createDriver());
        truckDelivery.setId(1);
        truckDelivery.setLoadNumber("45");
        truckDelivery.setPallets(34);
        truckDelivery.setPickupNumber("45");
        truckDelivery.setStateFrom("NJ");
        truckDelivery.setStateTo("MA");
        truckDelivery.setStatus(DeliveryStatus.CREATED);
        truckDelivery.setTimeFrom(new Timestamp(new Date().getTime()));
        truckDelivery.setTimeTo(new Timestamp(new Date().getTime()));
        truckDelivery.setTrailerNumber("456");
        truckDelivery.setTrailerPlateNumber("hg67dkd");
        truckDelivery.setTruck(createDeviceLightweight());
        truckDelivery.setWeight(344);
        truckDelivery.setZipcodeFrom("08861");
        truckDelivery.setZipcodeTo("08861");
        return truckDelivery;
    }

    public static DeviceLightweight createDeviceLightweight() {
        DeviceLightweight deviceLightweight = new DeviceLightweight();
        deviceLightweight.setId(1);
        deviceLightweight.setAccount(createAccountLightweight());
        return deviceLightweight;
    }

    public static Driver createDriver() {
        Driver driver = new Driver();
        driver.setId(1);
        driver.setAccount(createAccountLightweight());
        driver.setLogin("login");
        driver.setPass("password");
        driver.setDisplayName("name");
        driver.setDriverCode("TRUCK_DRIVER");
        return driver;
    }

    private static <T> T readValue(String path, Class<T> valueType) {
        try {
            return objectMapper.readValue(new File(path), valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}