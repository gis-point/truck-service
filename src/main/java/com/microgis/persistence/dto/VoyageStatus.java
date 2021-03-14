package com.microgis.persistence.dto;

public enum VoyageStatus {

    IDLE("voyage.status.free.idle"),
    CREATED("voyage.status.create.wait"),
    RUN("voyage.status.run.in.time"),
    RUN_AHEAD("voyage.status.run.ahead"),
    RUN_LATENESS("voyage.status.run.lateness"),
    RUN_WITH_DEFLECTION("voyage.status.run.deflection"),
    COMPLETED_IN_TIME("voyage.status.completed.in.time"),
    COMPLETED_AHEAD("voyage.status.completed.ahead"),
    COMPLETED_LATENESS("voyage.status.completed.lateness"),
    INTERRUPTED("voyage.status.completed.interrupted"),
    EXPIRED("general.status.extended.expired"),
    REGISTERED("voyage.status.registered"),
    DRAFTED("voyage.status.drafted");

    private final String i18nCode;

    VoyageStatus(String i18nCode) {
        this.i18nCode = i18nCode;
    }

    public String getI18nCode() {
        return i18nCode;
    }

    public boolean isRun() {
        return this == RUN || this == RUN_AHEAD || this == RUN_LATENESS || this == RUN_WITH_DEFLECTION;
    }

    public boolean isCompleted() {
        return this == COMPLETED_IN_TIME
                || this == COMPLETED_AHEAD
                || this == COMPLETED_LATENESS
                || this == INTERRUPTED
                || this == EXPIRED;
    }

}