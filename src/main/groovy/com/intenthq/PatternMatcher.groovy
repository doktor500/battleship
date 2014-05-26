package com.intenthq

import java.util.regex.Matcher
import java.util.regex.Pattern

class PatternMatcher {

    static List findAll(String regEx, String input) {
        List matches = []
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) { matches << matcher.group() }
        matches
    }
}
