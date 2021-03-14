package com.microgis.util;

import com.microgis.persistence.dto.TimeZoneList;
import io.micrometer.core.instrument.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class TimeZoneListConverter implements AttributeConverter<TimeZoneList, String> {

    @Override
    public String convertToDatabaseColumn(TimeZoneList zone) {
        return Objects.requireNonNullElse(zone, TimeZoneList.EUROPE_KIEV).getCode();
    }

    @Override
    public TimeZoneList convertToEntityAttribute(String s) {
        var zone = TimeZoneList.EUROPE_KIEV;
        if (StringUtils.isNotBlank(s)) {
            for (TimeZoneList timeZone : TimeZoneList.values()) {
                if (timeZone.getCode().equals(s)) {
                    zone = timeZone;
                }
            }
        }
        return zone;
    }
}