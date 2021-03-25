package com.microgis.controller;

import com.microgis.controller.dto.TruckDeliveryInfo;
import com.microgis.service.TruckDeliveryService;
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
public class DeliveryInformationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryInformationController.class);

    private final TruckDeliveryService truckDeliveryService;

    /**
     * Processing delivery information
     *
     * @param truckDeliveryInfo information about delivery
     * @return 200 if everything ok
     */
    @PostMapping("/deliveryInfo")
    public ResponseEntity<Object> deliveryInfo(Authentication authentication,
                                               @RequestBody TruckDeliveryInfo truckDeliveryInfo) {
        LOGGER.info("Get delivery information - {}", truckDeliveryInfo);
        truckDeliveryService.saveOrUpdateDeliveryInformation(truckDeliveryInfo);
        LOGGER.info("Delivery information processed");
        return ResponseEntity.ok().build();
    }

}