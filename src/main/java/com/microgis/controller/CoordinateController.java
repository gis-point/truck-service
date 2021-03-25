package com.microgis.controller;

import com.microgis.controller.dto.MobileCoordinate;
import com.microgis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/truck-service")
@RequiredArgsConstructor
public class CoordinateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoordinateController.class);

    private final RedisService redisService;

    @PostMapping("/eventData")
    public ResponseEntity<Object> storeCoordinates(Authentication authentication,
                                                   @RequestBody MobileCoordinate mobileCoordinate) {
        LOGGER.info("Process coordinates for driver - {}, coordinate - {}", null, mobileCoordinate);
        redisService.writeToRedis(mobileCoordinate);
        return ResponseEntity.ok().build();
    }

}