package com.intenthq.battleship.domain

class Dimension {

    Integer width
    Integer height

    Dimension(width, height) {
        this.width = width.toInteger()
        this.height = height.toInteger()
    }
}
