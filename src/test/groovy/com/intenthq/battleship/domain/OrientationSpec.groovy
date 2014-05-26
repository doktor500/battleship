package com.intenthq.battleship.domain

import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

import static com.intenthq.battleship.domain.Orientation.*

class OrientationSpec extends Specification {

    Should 'return all orientation symbols'() {
        expect:
        Orientation.symbols == 'NESW'
    }

    @Unroll
    Should 'return orientation from the corresponding symbol'() {
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
    Should 'return current orientation symbol'() {
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
    Should 'rotate orientation to the left'() {
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
    Should 'rotate orientation to the right'() {
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
    Should 'return if orientation is horizontal'() {
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
    Should 'return orientation string value'() {
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
