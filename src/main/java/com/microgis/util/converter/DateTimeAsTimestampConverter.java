package com.microgis.util.converter;

import com.microgis.util.Constants;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;

public class DateTimeAsTimestampConverter implements AttributeConverter<DateTime, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern(Constants.DATA_TIME_FORMAT_IN_BASE)
            .withZoneUTC();

    @Override
    public String convertToDatabaseColumn(DateTime dateTime) {
        try {
            return dateTime == null ? null : FORMATTER.print(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DateTime convertToEntityAttribute(String timestamp) {
        try {
            return timestamp == null ? null : FORMATTER.parseDateTime(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}