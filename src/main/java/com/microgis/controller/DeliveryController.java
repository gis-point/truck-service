package com.microgis.controller;

import com.microgis.controller.dto.DeliveryResponse;
import com.microgis.service.TruckDeliveryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/truck-service")
@Validated
@RequiredArgsConstructor
public class DeliveryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryController.class);

    private final TruckDeliveryService truckDeliveryService;

    @GetMapping("/delivery")
    public ResponseEntity<DeliveryResponse> getDeliveryInformation(Authentication authentication) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        Map<String, Object> attributes = token.getTokenAttributes();
        String username = (String) attributes.get("username");
        LOGGER.info("Get delivery information for user - {}", username);
        var deliveryInfo = truckDeliveryService.getDeliveryResponse(username);
        return deliveryInfo == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(deliveryInfo);
    }

}