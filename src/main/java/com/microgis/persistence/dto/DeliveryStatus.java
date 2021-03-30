package com.microgis.persistence.dto;

import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ToString
public enum DeliveryStatus {

    CREATED("created"),
    COMPLETED("completed"),
    DELETED("deleted");

    private final String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

    private static final Map<String, DeliveryStatus> LOOKUP_MAP = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(env -> LOOKUP_MAP.put(env.getStatus(), env));
    }

    public static DeliveryStatus getDeliveryStatus(String url) {
        return LOOKUP_MAP.get(url);
    }

    public String getStatus() {
        return status;
    }

}