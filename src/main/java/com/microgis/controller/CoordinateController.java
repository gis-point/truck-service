package com.microgis.controller;

import com.microgis.controller.dto.MobileCoordinate;
import com.microgis.service.DriverService;
import com.microgis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/truck-service")
@RequiredArgsConstructor
public class CoordinateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoordinateController.class);

    private final RedisService redisService;

    private final DriverService driverService;

    @PostMapping("/eventData")
    public ResponseEntity<Object> storeCoordinates(@RequestHeader(name = "Authorization") String token,
                                                   @RequestBody MobileCoordinate mobileCoordinate) {
        LOGGER.info("Process coordinates for driver - {}, coordinate - {}", null, mobileCoordinate);
        redisService.writeToRedis(mobileCoordinate);
        return ResponseEntity.ok().build();
    }

}