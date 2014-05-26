package com.intenthq

import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

class StringUtilsSpec extends Specification {

    @Unroll
    Should 'camelize a string'() {
        expect:
        StringUtils.camelize(text) == textCamelized

        where:
        text                    | textCamelized
        'an_example'            | 'anExample'
        'a_longer_test_example' | 'aLongerTestExample'
    }
}
