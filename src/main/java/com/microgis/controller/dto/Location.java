package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private long time;

    private double latitude;

    private double longitude;

    private double speed;

    private double bearing;

    private Boolean isReal;

}