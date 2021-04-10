package com.microgis.service;

import com.microgis.controller.dto.DomainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainService {

    @Value("${volo.url}")
    private String url;

    private final RestTemplate restTemplate;

    /**
     * Get list of domains
     *
     * @return list of domains
     */
    public List<DomainResponse> getDomains() {
        ResponseEntity<List<DomainResponse>> responseEntity = restTemplate.exchange(url + "/accounts", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<DomainResponse>>() {
                });
        return responseEntity.getStatusCode() == HttpStatus.OK ? responseEntity.getBody() : null;
    }

}