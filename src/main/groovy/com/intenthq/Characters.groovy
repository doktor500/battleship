package com.intenthq

class Characters {

    static final EMPTY = ''
    static final SPACE = ' '
    static final COMMA = ','
    static final OPENED_BRACKET = '('
    static final CLOSED_BRACKET = ')'
    static final NEW_LINE = '\n'

    static final getDelimiters() {
        [OPENED_BRACKET, COMMA, CLOSED_BRACKET].join()
    }
}
