package com.microgis.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DomainResponse {

    private String id;

    private String domain;

    @JsonIgnore
    public String getId() {
        return id;
    }

}