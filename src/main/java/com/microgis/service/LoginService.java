package com.microgis.service;

import com.microgis.controller.dto.Login;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    @Value("${volo.url}")
    private String url;

    private final RestTemplate restTemplate;

    /**
     * Call client api for user authorization
     *
     * @param login user authorization information
     * @return exist user or not
     */
    public boolean checkLoginInformation(Login login) {
        LOGGER.info("Sent authorization information to volo - {}", login);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("");
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, headers, Object.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            var password = responseEntity.getBody().toString();
            if (password.equals(login.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
    }
}