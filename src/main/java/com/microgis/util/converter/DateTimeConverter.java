package com.microgis.util.converter;

import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;

public class DateTimeConverter implements AttributeConverter<DateTime, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DateTime dateTime) {
        return dateTime != null ? (int) (dateTime.getMillis() / 1000L) : 0;
    }

    @Override
    public DateTime convertToEntityAttribute(Integer time) {
        return time != null ? new DateTime(time * 1000L) : new DateTime(0);
    }

}