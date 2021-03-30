package com.microgis.controller;

import com.microgis.controller.dto.TruckDeliveryInfo;
import com.microgis.service.TruckDeliveryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> createDeliveryInfo(Authentication authentication,
                                                     @RequestBody TruckDeliveryInfo truckDeliveryInfo) {
        LOGGER.info("Get delivery information - {}", truckDeliveryInfo);
        truckDeliveryService.saveOrUpdateDeliveryInformation(truckDeliveryInfo);
        LOGGER.info("Delivery information processed");
        return ResponseEntity.ok().build();
    }

    /**
     * Updating delivery information
     *
     * @param truckDeliveryInfo information about delivery
     * @return 200 if everything ok
     */
    @PutMapping("/deliveryInfo")
    public ResponseEntity<Object> updateDeliveryInfo(Authentication authentication,
                                                     @RequestBody TruckDeliveryInfo truckDeliveryInfo) {
        LOGGER.info("Updating delivery information - {}", truckDeliveryInfo);
        truckDeliveryService.saveOrUpdateDeliveryInformation(truckDeliveryInfo);
        LOGGER.info("Delivery information updated");
        return ResponseEntity.ok().build();
    }

}