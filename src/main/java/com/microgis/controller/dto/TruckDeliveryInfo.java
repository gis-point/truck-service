package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TruckDeliveryInfo {

    @NotNull
    private Integer loadNumber;

    @NotNull
    private TruckInfo truck;

    @NotNull
    private DriverInfo driver;

    @NotNull
    private BrokerInfo broker;

    @NotNull
    private InfoFrom informationFrom;

    @NotNull
    private InfoTo informationTo;

    @NotNull
    private String status;

    private Integer pallets;

    private Integer weight;

    private String cargoType;

}