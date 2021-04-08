package com.microgis.service;

import com.microgis.config.RedisConfig;
import com.microgis.controller.dto.Location;
import com.microgis.controller.dto.MobileCoordinate;
import com.microgis.persistence.dto.EventData;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    //set to 3 because of condition in Track app
    private static final int MIN_NUMBER_SATELLITE = 3;

    private final RedisConfig redisConfig;

    private final DeviceService deviceService;

    /**
     * Add coordinates to redis queue
     *
     * @param mobileCoordinate coordinates which should be added to redis queue
     */
    @SuppressWarnings({"java:S3864", "java:S5411"})
    public void writeToRedis(MobileCoordinate mobileCoordinate) {
        var deviceLightweight = deviceService.findById(mobileCoordinate.getDeviceId());
        String deviceId = deviceLightweight != null ? String.valueOf(deviceLightweight.getLegacyDeviceId()) : "";
        LOGGER.info("Post info to redis with values - {}", mobileCoordinate);
        var redissonClient = redisConfig.getClient();
        RQueue<EventData> queue = redissonClient.getQueue("RawData");
        mobileCoordinate.getLocations()
                .stream()
                .filter(location -> filterRealCoordinates(mobileCoordinate, location))
                .map(location -> fillDataForRedis(mobileCoordinate, deviceId, location))
                .forEach(eventData -> {
                    LOGGER.info("Adding to redis - {}", eventData);
                    queue.add(eventData);
                });
    }

    /**
     * Fill information for redis
     *
     * @param mobileCoordinate coordinates information
     * @param deviceId         device id
     * @param location         gps coordinates
     * @return dto for redis
     */
    private EventData fillDataForRedis(MobileCoordinate mobileCoordinate, String deviceId, Location location) {
        return EventData.builder()
                .accountID(mobileCoordinate.getAccount())
                .deviceID(deviceId)
                .timestamp(location.getTime() / 1000L)
                .statusCode(61714)
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .speedKPH((int) location.getSpeed())
                .heading(0)
                .altitude(0)
                .rawData(null)
                .creationTime(String.valueOf(mobileCoordinate.getCreationTime() / 1000L))
                .HDOP(0)
                .satelliteCount(MIN_NUMBER_SATELLITE)
                .processingStatus(0)
                .deviceIndex(mobileCoordinate.getDeviceId())
                .build();
    }

    /**
     * Filter unreal coordinates
     *
     * @param mobileCoordinate coordinates info
     * @param location         gps coordinates
     * @return true/false
     */
    @SuppressWarnings("java:S5411")
    private Boolean filterRealCoordinates(MobileCoordinate mobileCoordinate, Location location) {
        if (!location.getIsReal()) {
            LOGGER.info("Coordinates is non real, username - {}", mobileCoordinate.getUsername());
        }
        return location.getIsReal();
    }

}