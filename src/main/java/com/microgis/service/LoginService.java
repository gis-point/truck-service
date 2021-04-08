package com.microgis.service;

import com.microgis.controller.dto.LoginRequest;
import com.microgis.controller.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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
    public LoginResponse checkLoginInformation(LoginRequest login) throws ResponseStatusException {
        LOGGER.info("Sent authorization information to volo - {}", login);
        ResponseEntity<LoginResponse> responseEntity = restTemplate.postForEntity("https//" + login.getDomain() +
                "/api/login", login, LoginResponse.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        return null;
    }

}