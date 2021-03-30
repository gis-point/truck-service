package com.microgis.service;

import com.microgis.controller.dto.Login;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    private final RestTemplate restTemplate;

    /**
     * Call client api for user authorization
     *
     * @param login user authorization information
     * @return exist user or not
     */
    public void checkLoginInformation(Login login) {
        LOGGER.info("Sent authorization information to volo - {}", login);
    }
}