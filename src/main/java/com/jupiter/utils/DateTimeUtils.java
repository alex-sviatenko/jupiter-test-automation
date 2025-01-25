package com.jupiter.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ofPattern;

public class DateTimeUtils {

    /**
     * DateTime pattern example: "2025-01-16T17:26:52.912Z"
     */
    public static final DateTimeFormatter DATE_TIME_SSS_FORMATTER = ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    public static LocalDateTime parseDateTime(String dateTimeString, DateTimeFormatter dateTimeFormatter) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTimeString, dateTimeFormatter);

        return offsetDateTime.toLocalDateTime();
    }

    public static LocalDate getDateNow() {
        return LocalDate.now(UTC);
    }

    public static LocalDate getFutureDate(Integer daysToAdd) {
        return LocalDate.now(UTC).plusDays(daysToAdd);
    }

    public static LocalDate getPastDate(Integer minusDays) {
        return LocalDate.now(UTC).minusDays(minusDays);
    }
}
