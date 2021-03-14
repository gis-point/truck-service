package com.microgis.persistence.dto;

public enum Status {

    DRIVER,
    CONDUCTOR,
    RESPONSIBLE,
    MECHANIC,
    DOCTOR,
    MANAGER;

    public String getId() {
        return this.toString();
    }

    public String getName() {
        return this.toString().toLowerCase();
    }

}