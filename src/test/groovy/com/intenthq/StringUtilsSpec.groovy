package com.intenthq

import spock.lang.Specification
import spock.lang.Unroll

class StringUtilsSpec extends Specification {

    @Unroll
    void 'camelizes a string'() {
        expect:
        StringUtils.camelize(text) == textCamelized

        where:
        text                    | textCamelized
        'an_example'            | 'anExample'
        'a_longer_test_example' | 'aLongerTestExample'
    }
}
