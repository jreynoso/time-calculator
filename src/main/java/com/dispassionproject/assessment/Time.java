package com.dispassionproject.assessment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
class Time {

    static final String AM = "AM";
    static final String PM = "PM";

    private int hours;
    private int minutes;

    void adjustHours(int hoursOffset) {
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

    void adjustMinutes(int minsOffset) {
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

    String to12HourFormat() {
        final int convertedHours = hours == 0 ? 12 : hours <= 12 ? hours : hours - 12;
        return String.format("%d:%02d %s", convertedHours, minutes, hours < 12 ? AM : PM);
    }

}
