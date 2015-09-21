package com.intenthq.battleship.domain

import spock.lang.Specification
import spock.lang.Unroll

import static com.intenthq.battleship.domain.Orientation.*

class PositionSpec extends Specification {

    void 'rotates current position to left'() {
        given:
        def position = new Position(orientation: NORTH)

        when:
        position.rotateLeft()

        then:
        position.orientation == WEST
    }

    void 'rotates current position to right'() {
        given:
        def position = new Position(orientation: NORTH)

        when:
        position.rotateRight()

        then:
        position.orientation == EAST
    }

    void 'returns X axis coordinate'() {
        given:
        def (x, y) = [2, 3]
        def position = new Position(coordinate: new Coordinate(x, y))

        expect:
        position.x == x
    }

    void 'returns Y axis coordinate'() {
        given:
        def (x, y) = [2, 3]
        def position = new Position(coordinate: new Coordinate(x, y))

        expect:
        position.y == y
    }

    void 'sets next north coordinate'() {
        given:
        def (width, height) = [3, 5]
        def dimension = new Dimension(width, height)
        def currentCoordinate = Mock(Coordinate)
        def nextCoordinate = Mock(Coordinate)
        def position = new Position(coordinate: currentCoordinate, orientation: NORTH)

        when:
        position.next(dimension)

        then:
        1 * currentCoordinate.nextNorthCoordinate(height) >> nextCoordinate
        position.coordinate == nextCoordinate
    }

    void 'sets next south coordinate'() {
        given:
        def (width, height) = [3, 5]
        def dimension = new Dimension(width, height)
        def currentCoordinate = Mock(Coordinate)
        def nextCoordinate = Mock(Coordinate)
        def position = new Position(coordinate: currentCoordinate, orientation: SOUTH)

        when:
        position.next(dimension)

        then:
        1 * currentCoordinate.nextSouthCoordinate(height) >> nextCoordinate
        position.coordinate == nextCoordinate
    }

    void 'sets next east coordinate'() {
        given:
        def (width, height) = [3, 5]
        def dimension = new Dimension(width, height)
        def currentCoordinate = Mock(Coordinate)
        def nextCoordinate = Mock(Coordinate)
        def position = new Position(coordinate: currentCoordinate, orientation: EAST)

        when:
        position.next(dimension)

        then:
        1 * currentCoordinate.nextEastCoordinate(width) >> nextCoordinate
        position.coordinate == nextCoordinate
    }

    void 'sets next west coordinate'() {
        given:
        def (width, height) = [3, 5]
        def dimension = new Dimension(width, height)
        def currentCoordinate = Mock(Coordinate)
        def nextCoordinate = Mock(Coordinate)
        def position = new Position(coordinate: currentCoordinate, orientation: WEST)

        when:
        position.next(dimension)

        then:
        1 * currentCoordinate.nextWestCoordinate(width) >> nextCoordinate
        position.coordinate == nextCoordinate
    }

    @Unroll
    void 'returns next position'() {
        given:
        def (width, height) = [3, 5]
        def dimension = new Dimension(width, height)

        when:
        currentPosition.next(dimension)

        then:
        currentPosition == nextPosition

        where:
        currentPosition       | nextPosition
        position(0, 0, NORTH) | position(0, 1, NORTH)
        position(0, 0, SOUTH) | position(0, 5, SOUTH)
        position(0, 0, EAST)  | position(1, 0, EAST)
        position(0, 0, WEST)  | position(3, 0, WEST)

        position(3, 0, NORTH) | position(3, 1, NORTH)
        position(3, 0, SOUTH) | position(3, 5, SOUTH)
        position(3, 0, EAST)  | position(0, 0, EAST)
        position(3, 0, WEST)  | position(2, 0, WEST)

        position(0, 5, NORTH) | position(0, 0, NORTH)
        position(0, 5, SOUTH) | position(0, 4, SOUTH)
        position(0, 5, EAST)  | position(1, 5, EAST)
        position(0, 5, WEST)  | position(3, 5, WEST)

        position(3, 5, NORTH) | position(3, 0, NORTH)
        position(3, 5, SOUTH) | position(3, 4, SOUTH)
        position(3, 5, EAST)  | position(0, 5, EAST)
        position(3, 5, WEST)  | position(2, 5, WEST)
    }

    private position(x, y, orientation) {
        new Position(coordinate: new Coordinate(x, y), orientation: orientation)
    }
}
