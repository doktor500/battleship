package com.intenthq.battleship.domain

import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

class CoordinateSpec extends Specification {

    @Unroll
    Should 'return next north coordinate generating a cycle when barrier is reach'() {
        given:
        def (x, y) = currentCoordinate
        def (nextX, nextY) = nextCoordinate

        expect:
        new Coordinate(x, y).nextNorthCoordinate(height) == new Coordinate(nextX, nextY)

        where:
        currentCoordinate | height || nextCoordinate
        [0, 1]            | 1      || [0, 0]
        [0, 2]            | 2      || [0, 0]
        [0, 0]            | 1      || [0, 1]
        [0, 1]            | 2      || [0, 2]
    }

    @Unroll
    Should 'return next south coordinate generating a cycle when barrier is reach'() {
        given:
        def (x, y) = currentCoordinate
        def (nextX, nextY) = nextCoordinate

        expect:
        new Coordinate(x, y).nextSouthCoordinate(height) == new Coordinate(nextX, nextY)

        where:
        currentCoordinate | height || nextCoordinate
        [0, 0]            | 1      || [0, 1]
        [0, 0]            | 2      || [0, 2]
        [0, 1]            | 2      || [0, 0]
        [0, 2]            | 2      || [0, 1]
    }

    @Unroll
    Should 'return next east coordinate generating a cycle when barrier is reach'() {
        given:
        def (x, y) = currentCoordinate
        def (nextX, nextY) = nextCoordinate

        expect:
        new Coordinate(x, y).nextEastCoordinate(width) == new Coordinate(nextX, nextY)

        where:
        currentCoordinate | width || nextCoordinate
        [1, 0]            | 1     || [0, 0]
        [2, 0]            | 2     || [0, 0]
        [0, 0]            | 1     || [1, 0]
        [1, 0]            | 2     || [2, 0]
    }

    @Unroll
    Should 'return next west coordinate generating a cycle when barrier is reach'() {
        given:
        def (x, y) = currentCoordinate
        def (nextX, nextY) = nextCoordinate

        expect:
        new Coordinate(x, y).nextWestCoordinate(width) == new Coordinate(nextX, nextY)

        where:
        currentCoordinate | width || nextCoordinate
        [0, 0]            | 1     || [1, 0]
        [0, 0]            | 2     || [2, 0]
        [1, 0]            | 2     || [0, 0]
        [2, 0]            | 2     || [1, 0]
    }
}
