package com.dispassionproject.assessment

import com.github.javafaker.Faker
import spock.lang.Specification

class TimeSpec extends Specification {

    def faker = new Faker()

    def "should convert 12H formatted hours to 24H formatted hours"() {
        when:
        def hours = Time.getHours(formattedTime)

        then:
        hours == expectedHours

        where:
        formattedTime || expectedHours
        '12:00 AM'    || 0
        '1:00 AM'     || 1
        '2:00 AM'     || 2
        '3:00 AM'     || 3
        '4:00 AM'     || 4
        '5:00 AM'     || 5
        '6:00 AM'     || 6
        '7:00 AM'     || 7
        '8:00 AM'     || 8
        '9:00 AM'     || 9
        '10:00 AM'    || 10
        '11:00 AM'    || 11
        '12:00 PM'    || 12
        '1:00 PM'     || 13
        '2:00 PM'     || 14
        '3:00 PM'     || 15
        '4:00 PM'     || 16
        '5:00 PM'     || 17
        '6:00 PM'     || 18
        '7:00 PM'     || 19
        '8:00 PM'     || 20
        '9:00 PM'     || 21
        '10:00 PM'    || 22
        '11:00 PM'    || 23
    }

    def "should extract minutes from 12H formatted time"() {
        given:
        def hour = faker.number().numberBetween(1, 12)
        def minutes = faker.number().numberBetween(0, 59)
        def period = faker.options().option("AM", "PM")

        when:
        def extractedMinutes = Time.getMinutes("${hour}:${minutes} ${period}")

        then:
        extractedMinutes == minutes
    }

}
