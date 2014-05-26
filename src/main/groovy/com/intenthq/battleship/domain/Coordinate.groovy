package com.intenthq.battleship.domain

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Coordinate {

    Integer x
    Integer y

    Coordinate(x, y) {
        this.x = x.toInteger()
        this.y = y.toInteger()
    }

    Coordinate nextNorthCoordinate(Integer max) {
        def nextY = inRangeOrMin(y.next(), max)
        new Coordinate(x, nextY)
    }

    Coordinate nextSouthCoordinate(Integer max) {
        def previousY = inRangeOrMax(y.previous(), max)
        new Coordinate(x, previousY)
    }

    Coordinate nextEastCoordinate(Integer max) {
        def nextX = inRangeOrMin(x.next(), max)
        new Coordinate(nextX, y)
    }

    Coordinate nextWestCoordinate(Integer max) {
        def previousX = inRangeOrMax(x.previous(), max)
        new Coordinate(previousX, y)
    }

    private inRangeOrMax(value, maxValue) {
        value in (0..maxValue) ? value : maxValue
    }

    private inRangeOrMin(value, maxValue) {
        value in (0..maxValue) ? value : 0
    }
}