package com.intenthq.battleship.domain

import spock.lang.Specification
import spock.lang.Unroll

class CoordinateSpec extends Specification {

    @Unroll
    void 'returns next north coordinate generating a cycle when barrier is reach'() {
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
    void 'returns next south coordinate generating a cycle when barrier is reach'() {
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
    void 'returns next east coordinate generating a cycle when barrier is reach'() {
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
    void 'returns next west coordinate generating a cycle when barrier is reach'() {
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
