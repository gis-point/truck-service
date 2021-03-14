package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TruckInfo {

    private String truckNumber;

    private String trailerNumber;

    private String plateNumber;

    private String trailerPlateNumber;

}