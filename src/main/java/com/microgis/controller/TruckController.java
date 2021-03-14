package com.microgis.controller;

import com.microgis.controller.dto.TruckDeliveryInfo;
import com.microgis.service.TruckDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/truck-service")
@RequiredArgsConstructor
public class TruckController {

    private final TruckDeliveryService truckDeliveryService;

    @PostMapping("/deliveryInfo")
    public ResponseEntity<?> deliveryInfo(@RequestBody TruckDeliveryInfo truckDeliveryInfo) {
        truckDeliveryService.saveDeliveryInformation(truckDeliveryInfo);
        return ResponseEntity.ok().build();
    }

}