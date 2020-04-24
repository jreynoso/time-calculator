package com.dispassionproject.sandbox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Time {

    private static final String TIME_FORMAT = "^(1[0-2]|[1-9]):[0-5][0-9] (AM|PM)$";
    private static final String AM = "AM";
    private static final String PM = "PM";

    private int hours;
    private int minutes;

    public static Time fromFormattedTime(final String formatted12HourTime) {
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
        final boolean isAm = formatted12HourTime.endsWith(AM);
        final int hours = Integer.parseInt(formatted12HourTime.split(":", 2)[0]);
        final int offset = isAm ? 0 : 12;
        return hours == 12 ? (isAm ? 0 : 12) : hours + offset;
    }

    private static int getMinutes(final String formatted12HourTime) {
        return Integer.parseInt(formatted12HourTime.split("[:|\\s]", 3)[1]);
    }

    public static boolean isValid12HourTime(final String formatted12HourTime) {
        return formatted12HourTime != null && formatted12HourTime.matches(TIME_FORMAT);
    }

    public void adjustHours(int hoursOffset) {
        int adjustedHours = hours + hoursOffset;
        if (adjustedHours < 0) {
            hours = 24 + adjustedHours;
        } else if (adjustedHours > 23) {
            hours = adjustedHours - 24;
        }
        else {
            hours = adjustedHours;
        }
    }

    public void adjustMinutes(int minsOffset) {
        int adjustedMinutes = minutes + minsOffset;
        if (adjustedMinutes < 0) {
            adjustHours(-1);
            minutes = 60 + adjustedMinutes;
        } else if (adjustedMinutes > 59) {
            adjustHours(1);
            minutes = adjustedMinutes - 60;
        } else {
            minutes = adjustedMinutes;
        }
    }

    public String to12HourFormat() {
        final int convertedHours = hours == 0 ? 12 : hours <= 12 ? hours : hours - 12;
        return String.format("%d:%02d %s", convertedHours, minutes, hours < 12 ? AM : PM);
    }
}
