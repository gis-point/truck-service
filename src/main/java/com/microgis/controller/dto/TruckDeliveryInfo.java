package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TruckDeliveryInfo {

    private Integer loadNumber;

    private TruckInfo truck;

    private DriverInfo driver;

    private String trailerNumber;

    private String addressLineFrom;

    private String cityFrom;

    private String stateFrom;

    private String timeFrom;

    private String addressLineTo;

    private String cityTo;

    private String stateTo;

    private String timeTo;

    private String status;

}