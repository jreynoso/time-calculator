package com.dispassionproject.sandbox

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class TimeCalculatorSpec extends Specification {

    def "should calculate a new time from #testTime, offset by #minOffset minutes"() {
        when:
        def offsetTime = TimeCalculator.addMinutes(testTime, minOffset)

        then:
        offsetTime == expectedTime

        where:
        testTime   | minOffset || expectedTime
        '12:00 AM' | -5        || '11:55 PM'
        '12:55 AM' | 5         || '1:00 AM'
        '12:00 PM' | -5        || '11:55 AM'
        '12:55 PM' | 5         || '1:00 PM'
        '11:59 AM' | 1         || '12:00 PM'
        '11:59 PM' | 1         || '12:00 AM'
        '1:00 PM'  | -1        || '12:59 PM'
        '1:00 AM'  | -1        || '12:59 AM'
    }

}
