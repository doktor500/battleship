package com.intenthq

import spock.lang.Specification

class CharactersSpec extends Specification {

    void 'returns delimiter characters'() {
        expect:
        Characters.delimiters == '(,)'
    }
}
