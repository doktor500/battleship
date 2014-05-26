package com.intenthq

class StringUtils {

    private static final UNDERSCORE = '_'

    static String camelize(String value) {
        def words = value.split(UNDERSCORE)*.toLowerCase()
        (words.head() + words.tail()*.capitalize().join())
    }
}
