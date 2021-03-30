package com.microgis.persistence.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("com.microgis.telematic.model.EventData")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class EventData {

    private String accountID;

    private String deviceID;

    private long timestamp;

    private int statusCode;

    private double latitude;

    private double longitude;

    private double speedKPH;

    private double heading;

    private double altitude;

    private String rawData;

    private String creationTime;

    @SuppressWarnings("java:S116")
    private double HDOP;

    private int satelliteCount;

    private int processingStatus;

    private int deviceIndex;

}