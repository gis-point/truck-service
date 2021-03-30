package com.microgis.controller.dto;

import lombok.Data;

@Data
public class DeliveryResponse {

    /**
     * truck number
     */
    private int deviceId;

    /**
     * driver name
     */
    private String driverName;

    /**
     * address information where from deliver
     */
    private AddressLine addressLineFrom;

    /**
     * address information where to deliver
     */
    private AddressLine addressLineTo;

    /**
     * cargo weight
     */
    private Integer weight;

    /**
     * cargo type
     */
    private String cargoType;

}