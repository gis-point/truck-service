package com.microgis.controller.dto;

import lombok.Data;

@Data
public class DeliveryResponse {

    private String driverName;

    private InfoFrom infoFrom;

    private InfoTo infoTo;

    private Integer pallets;

    private Integer weight;

    private String cargoType;

}