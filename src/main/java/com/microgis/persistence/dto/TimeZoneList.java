package com.microgis.persistence.dto;

public enum TimeZoneList {

    EUROPE_KIEV("Europe/Kiev"),
    US_HAWAII("US/Hawaii"),
    US_ALASKA("US/Alaska"),
    US_PACIFIC("US/Pacific"),
    US_ARIZONA("US/Arizona"),
    US_MOUNTAIN("US/Mountain"),
    US_CENTRAL("US/Central"),
    US_EASTERN("US/Eastern"),
    CANADA_PACIFIC("Canada/Pacific"),
    CANADA_MOUNTAIN("Canada/Mountain"),
    CANADA_CENTRAL("Canada/Central"),
    CANADA_EASTERN("Canada/Eastern"),
    CANADA_ATLANTIC("Canada/Atlantic"),
    MEXICO_BAJANORTE("Mexico/BajaNorte"),
    MEXICO_BAJASUR("Mexico/BajaSur"),
    MEXICO_GENERAL("Mexico/General"),
    EUROPE_ATHENS("Europe/Athens"),
    EUROPE_BERLIN("Europe/Berlin"),
    EUROPE_DUBLIN("Europe/Dublin"),
    EUROPE_HELSINKI("Europe/Helsinki"),
    EUROPE_LISBON("Europe/Lisbon"),
    EUROPE_LONDON("Europe/London"),
    EUROPE_MADRID("Europe/Madrid"),
    EUROPE_MOSCOW("Europe/Moscow"),
    EUROPE_OSLO("Europe/Oslo"),
    EUROPE_PARIS("Europe/Paris"),
    EUROPE_ROME("Europe/Rome"),
    EUROPE_STOCKHOLM("Europe/Stockholm"),
    EUROPE_ZURICH("Europe/Zurich"),
    UTC("UTC");

    TimeZoneList(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private final String code;

    private String id;

}