package com.microgis.persistence.dto;

public enum GroupType {

    SIMPLE(0, "group.type.simple"),
    MULTI(1, "group.type.multi"),
    DEFAULT(2, "group.type.default"),
    ROUTING(3, "group.type.routing"),
    SUBDIVISION(4, "group.type.division"),
    DEVICE_MARK(5, "group.type.device.mark");

    private final int code;

    private final String i18nKey;

    GroupType(int base, String i18nKey) {
        this.code = base;
        this.i18nKey = i18nKey;
    }

    public int getCode() {
        return code;
    }

    public static GroupType byOrdinal(int value) {
        return values()[value];
    }

    public String getI18nKey() {
        return i18nKey;
    }

}