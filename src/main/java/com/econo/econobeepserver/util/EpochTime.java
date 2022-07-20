package com.econo.econobeepserver.util;

import java.time.*;

public class EpochTime {

    private static final String KOREA_ZONE_OFFSET = "+09:00";

    public static long toEpochSecond(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.of(KOREA_ZONE_OFFSET));
    }

    public static long toEpochSecond(LocalDate localDate) {
        return localDate.toEpochSecond(LocalTime.parse("00:00:00"), ZoneOffset.of(KOREA_ZONE_OFFSET));
    }

    public static LocalDate toLocalDate(long epochSecond) {
        return Instant.ofEpochSecond(epochSecond).atZone(ZoneOffset.of(KOREA_ZONE_OFFSET)).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(long epochSecond) {
        return Instant.ofEpochSecond(epochSecond).atZone(ZoneOffset.of(KOREA_ZONE_OFFSET)).toLocalDateTime();
    }
}
