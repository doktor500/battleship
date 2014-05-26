package com.intenthq

import spock.lang.Specification

import java.lang.Void as Should

class CharactersSpec extends Specification {

    Should 'return delimiter characters'() {
        expect:
        Characters.delimiters == '(,)'
    }
}
