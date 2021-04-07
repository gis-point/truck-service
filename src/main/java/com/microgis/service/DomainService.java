package com.microgis.service;

import com.microgis.controller.dto.DomainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
public class DomainService {

    @Value("${volo.url}")
    private String url;

    private RestTemplate restTemplate;

    public List<DomainResponse> getDomains() {
        ResponseEntity<List<DomainResponse>> responseEntity = restTemplate.exchange(url+"/sites", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<DomainResponse>>() {
                });
        return responseEntity.getStatusCode() == HttpStatus.OK ? responseEntity.getBody() : null;
    }

}