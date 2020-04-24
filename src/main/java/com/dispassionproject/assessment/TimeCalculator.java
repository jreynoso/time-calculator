package com.dispassionproject.assessment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeCalculator {

    private static final String TIME_FORMAT = "^(1[0-2]|[1-9]):[0-5][0-9] (AM|PM)$";

    public static String addMinutes(final String formattedTime, final int offsetMinutes) {
        final Time time = fromFormattedTime(formattedTime);
        if (time == null) {
            throw new IllegalArgumentException(String.format("%s is not a valid time input.", formattedTime));
        }

        int hoursOffset = offsetMinutes / 60;
        int minutesOffset = offsetMinutes % 60;
        log.debug("offsets: hours={}, minutes={}", hoursOffset, minutesOffset);

        time.adjustHours(hoursOffset);
        time.adjustMinutes(minutesOffset);

        return time.to12HourFormat();
    }

    private static Time fromFormattedTime(final String formatted12HourTime) {
        if (!isValid12HourTime(formatted12HourTime)) {
            return null;
        }

        int hours = getHours(formatted12HourTime);
        int minutes = getMinutes(formatted12HourTime);
        return Time.builder()
                .hours(hours)
                .minutes(minutes)
                .build();
    }

    private static int getHours(final String formatted12HourTime) {
        final boolean isAm = formatted12HourTime.endsWith(Time.AM);
        final int hours = Integer.parseInt(formatted12HourTime.split(":", 2)[0]);
        final int offset = isAm ? 0 : 12;
        return hours == 12 ? (isAm ? 0 : 12) : hours + offset;
    }

    private static int getMinutes(final String formatted12HourTime) {
        return Integer.parseInt(formatted12HourTime.split("[:|\\s]", 3)[1]);
    }

    private static boolean isValid12HourTime(final String formatted12HourTime) {
        return formatted12HourTime != null && formatted12HourTime.matches(TIME_FORMAT);
    }

}
