package com.microgis.util.converter;

import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;

public class DateTimeConverter implements AttributeConverter<DateTime, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DateTime zone) {
        return zone != null ? (int) (zone.getMillis() / 1000L) : 0;
    }

    @Override
    public DateTime convertToEntityAttribute(Integer s) {
        return s != null ? new DateTime(s * 1000L) : new DateTime(0);
    }
}