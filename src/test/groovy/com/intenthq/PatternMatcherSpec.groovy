package com.intenthq

import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

import static com.intenthq.utils.VerbalExpression.regex

class PatternMatcherSpec extends Specification {

    @Unroll
    Should 'return every match found'() {
        expect:
        PatternMatcher.findAll(digitRegEx, text) == numbers

        where:
        text                   | numbers
        'ABC 123 CCC 888 A 19' | ['123', '888', '19']
        'a1-$38)'              | ['1', '38']
    }

    private getDigitRegEx() {
        regex()
        .capture()
        .digit().multiple('+')
        .build()
        .toString()
    }
}
