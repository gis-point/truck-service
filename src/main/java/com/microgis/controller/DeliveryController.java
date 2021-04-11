package com.microgis.controller;

import com.microgis.controller.dto.DeliveryResponse;
import com.microgis.controller.dto.DomainResponse;
import com.microgis.controller.dto.TruckDeliveryInfo;
import com.microgis.service.DomainService;
import com.microgis.service.TruckDeliveryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/truck-service")
@Validated
@RequiredArgsConstructor
public class DeliveryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryController.class);

    private final TruckDeliveryService truckDeliveryService;

    private final DomainService domainService;

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

    /**
     * Delivery information for driver
     *
     * @param authentication bearer token
     * @return delivery information for driver
     */
    @GetMapping("/delivery")
    public ResponseEntity<DeliveryResponse> getDeliveryInformation(Authentication authentication) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        Map<String, Object> attributes = token.getTokenAttributes();
        String username = (String) attributes.get("login");
        LOGGER.info("Get delivery information for user - {}", username);
        var deliveryInfo = truckDeliveryService.getDeliveryResponse(username);
        return deliveryInfo == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(deliveryInfo);
    }

    /**
     * Call volo service for available domains
     *
     * @return list of volo domains
     */
    @GetMapping("/domain")
    public ResponseEntity<List<DomainResponse>> getDomains() {
        LOGGER.info("Get domains info");
        var domains = domainService.getDomains();
        if (!CollectionUtils.isEmpty(domains)) {
            return ResponseEntity.ok(
                    domains
                            .stream()
                            .filter(domain -> StringUtils.hasText(domain.getDomain()))
                            .collect(Collectors.toList())
            );
        }
        return ResponseEntity.noContent().build();
    }

}