package com.microgis.controller;

import com.microgis.controller.dto.DeliveryResponse;
import com.microgis.service.TruckDeliveryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/truck-service")
@Validated
@RequiredArgsConstructor
public class DeliveryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryController.class);

    private final TruckDeliveryService truckDeliveryService;

    @GetMapping("/delivery/{loadNumber}")
    public ResponseEntity<DeliveryResponse> getDeliveryInformation(@PathVariable("loadNumber") Integer loadNumber) {
        LOGGER.info("Get delivery information for loadNumber - {}", loadNumber);
        var deliveryInfo = truckDeliveryService.getDeliveryResponse(loadNumber);
        if (deliveryInfo == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(deliveryInfo);
    }

}