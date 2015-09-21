package com.intenthq.battleship.domain

import spock.lang.Specification
import spock.lang.Unroll

import static com.intenthq.battleship.domain.Orientation.*

class OrientationSpec extends Specification {

    void 'returns all orientation symbols'() {
        expect:
        Orientation.symbols == 'NESW'
    }

    @Unroll
    void 'returns orientation from the corresponding symbol'() {
        expect:
        Orientation.fromSymbol(symbol) == orientation

        where:
        symbol | orientation
        'N'    | NORTH
        'S'    | SOUTH
        'E'    | EAST
        'W'    | WEST
        'X'    | null
    }

    @Unroll
    void 'returns current orientation symbol'() {
        expect:
        orientation.toSymbol() == symbol

        where:
        orientation | symbol
        NORTH       | 'N'
        SOUTH       | 'S'
        EAST        | 'E'
        WEST        | 'W'
    }

    @Unroll
    void 'rotates orientation to the left'() {
        expect:
        orientation.rotateLeft() == rotatedOrientation

        where:
        orientation | rotatedOrientation
        NORTH       | WEST
        EAST        | NORTH
        SOUTH       | EAST
        WEST        | SOUTH
    }

    @Unroll
    void 'rotates orientation to the right'() {
        expect:
        orientation.rotateRight() == rotatedOrientation

        where:
        orientation | rotatedOrientation
        NORTH       | EAST
        EAST        | SOUTH
        SOUTH       | WEST
        WEST        | NORTH
    }

    @Unroll
    void 'returns if orientation is horizontal'() {
        expect:
        orientation.horizontal == isHorizontal

        where:
        orientation | isHorizontal
        NORTH       | false
        EAST        | true
        SOUTH       | false
        WEST        | true
    }

    @Unroll
    void 'returns orientation string value'() {
        expect:
        orientation.toString() == orientationName

        where:
        orientation | orientationName
        NORTH       | 'North'
        EAST        | 'East'
        SOUTH       | 'South'
        WEST        | 'West'
    }
}
