package com.dispassionproject.sandbox;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeCalculator {

    public static String addMinutes(final String formattedTime, final int offsetMinutes) {
        final Time time = Time.fromFormattedTime(formattedTime);
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

}
