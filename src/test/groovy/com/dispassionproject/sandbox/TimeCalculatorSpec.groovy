package com.dispassionproject.sandbox

import com.github.javafaker.Faker
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class TimeCalculatorSpec extends Specification {

    @Shared
    def faker = new Faker()

    @Unroll
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
        '12:00 AM' | -61       || '10:59 PM'
        '11:00 PM' | 61        || '12:01 AM'
        '12:00 AM' | -1440     || '12:00 AM'
        '12:00 AM' | 1440      || '12:00 AM'
        '9:13 AM'  | 200       || '12:33 PM'
    }

    @Unroll
    def "should throw exception when receiving invalid time input: #testTime"() {
        when:
        TimeCalculator.addMinutes(testTime, faker.number().numberBetween(-100, 100))

        then:
        thrown IllegalArgumentException

        where:
        testTime << [null, faker.lorem().word(), '13:00', '01:00 AM', '0:00 AM', '10:1 PM', '1:00 am', '1:00 pm']
    }

}
