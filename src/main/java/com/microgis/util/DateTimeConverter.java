package com.microgis.util;

import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;

public class DateTimeConverter implements AttributeConverter<DateTime, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DateTime zone) {
        return zone != null ? (int) (zone.getMillis() / 1000L) : 0;
    }

    @Override
    public DateTime convertToEntityAttribute(Integer s) {
        if (s != null) {
            long misil = s * 1000L;
            return new DateTime(misil);
        } else {
            return new DateTime(0);
        }
    }
}