package com.microgis.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileCoordinate {

    @JsonIgnore
    private String account;

    @JsonIgnore
    private int deviceId;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private String deviceIndex;

    private long creationTime;

    private List<Location> locations = new ArrayList<>();

}