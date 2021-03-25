package com.microgis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microgis.controller.dto.*;
import com.microgis.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/truck-service")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private final JwtService jwtService;

    @Resource(name = "userService")
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public LoginResponse getToken(@RequestParam("user") String username, @RequestParam("password") String password) {
        LOGGER.info("Trying to login user with username - {} and password - {}", username, password);
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            Map<String, String> claims = new HashMap<>();
            claims.put("username", username);

            String authorities = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            claims.put("authorities", authorities);

            String jwt = jwtService.createJwtForClaims(username, claims);
            ObjectMapper mapper = new ObjectMapper();
            try {
                TruckDeliveryInfo truckDeliveryInfo=new TruckDeliveryInfo();

                TruckInfo TruckInfo=new TruckInfo();
                DriverInfo DriverInfo=new DriverInfo();
                BrokerInfo BrokerInfo=new BrokerInfo();
                AddressLine AddressLine =new AddressLine();
                truckDeliveryInfo.setDriver(DriverInfo);
                truckDeliveryInfo.setTruck(TruckInfo);
                truckDeliveryInfo.setBroker(BrokerInfo);
                truckDeliveryInfo.setAddressLineFrom(AddressLine);
                truckDeliveryInfo.setAddressLineTo(AddressLine);
                String s =mapper.writeValueAsString(truckDeliveryInfo);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new LoginResponse(jwt);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
    }

}