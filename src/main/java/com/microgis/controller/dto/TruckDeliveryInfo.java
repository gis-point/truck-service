package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TruckDeliveryInfo {

    /**
     * delivery load number
     */
    @NotNull
    private String loadNumber;

    /**
     * delivery pick-up number
     */
    private String pickupNumber;

    /**
     * truck and trailer information
     */
    @NotNull
    private TruckInfo truck;

    /**
     * driver information
     */
    @NotNull
    private DriverInfo driver;

    /**
     * broker information
     */
    @NotNull
    private BrokerInfo broker;

    /**
     * address information where from deliver
     */
    @NotNull
    private AddressLine addressLineFrom;

    /**
     * address information where to deliver
     */
    @NotNull
    private AddressLine addressLineTo;

    /**
     * delivery status
     * can be created, completed, deleted
     */
    @NotNull
    private String status;

    /**
     * number of pallets
     */
    private Integer pallets;

    /**
     * cargo weight
     */
    private Integer weight;

    /**
     * cargo type
     */
    private String cargoType;

}