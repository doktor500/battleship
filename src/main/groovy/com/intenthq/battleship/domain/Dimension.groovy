package com.intenthq.battleship.domain

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Dimension {

    Integer width
    Integer height

    Dimension(width, height) {
        this.width = width.toInteger()
        this.height = height.toInteger()
    }
}