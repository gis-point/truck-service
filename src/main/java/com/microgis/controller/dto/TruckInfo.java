package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TruckInfo {

    /**
     * truck number
     */
    @NotNull
    private Integer truckNumber;

    /**
     * truck plate number
     */
    @NotNull
    private String plateNumber;

    /**
     * trailer number
     */
    private String trailerNumber;

    /**
     * trailer plate number
     */
    private String trailerPlateNumber;

}