package com.intenthq.battleship.domain

import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

import static com.intenthq.battleship.domain.Orientation.EAST
import static com.intenthq.battleship.domain.Orientation.NORTH

class ShipSpec extends Specification {

    private static final INVALID_SHIP_COORDINATES = 'The ship must have a coordinate inside the board'

    def ship

    def setup() {
        ship = new Ship()
    }

    Should 'return if the ship is at a certain coordinate'() {
        given:
        def coordinate = new Coordinate(0, 0)
        def position = new Position(coordinate: coordinate)
        ship.position = position

        expect:
        ship.isAt(new Coordinate(0, 0))
        !ship.isAt(new Coordinate(0, 1))
    }

    Should 'mark a ship as sunk'() {
        expect:
        !ship.sunk

        when:
        ship.markAsSunk()

        then:
        ship.sunk
    }

    Should 'rotate ship to left'() {
        given:
        def position = Mock(Position)
        ship.position = position

        when:
        ship.rotateLeft()

        then:
        1 * position.rotateLeft()
    }

    Should 'rotate ship to right'() {
        given:
        def position = Mock(Position)
        ship.position = position

        when:
        ship.rotateRight()

        then:
        1 * position.rotateRight()
    }

    Should 'moves ship to next position'() {
        given:
        def dimension = Mock(Dimension)
        def position = Mock(Position)
        def board = Mock(Board)
        ship.board = board
        ship.position = position

        when:
        ship.move()

        then:
        1 * board.dimension >> dimension
        1 * position.next(dimension)
    }

    @Unroll
    Should 'return ship string value'() {
        expect:
        ship.format() == stringValue

        where:
        ship                                     | stringValue
        ship(new Coordinate(0, 0), NORTH, false) | '(0, 0, N) '
        ship(new Coordinate(2, 1), EAST, false)  | '(2, 1, E) '
        ship(new Coordinate(3, 3), EAST, true)   | '(3, 3, E) SUNK'
    }

    Should 'validate if the ship is located inside the board'() {
        given:
        def board = new Board(dimension: new Dimension(5, 5))
        def ship = new Ship(board: board, position: new Position(coordinate: new Coordinate(3, 3)))

        expect:
        ship.validate()
    }

    Should 'throw an invalid game exception if the ship is located outside the board'() {
        given:
        def board = new Board(dimension: new Dimension(3, 3))
        def ship = new Ship(board: board, position: new Position(coordinate: new Coordinate(5, 5)))

        when:
        ship.validate()

        then:
        def exception = thrown(InvalidGameException)
        exception.message == INVALID_SHIP_COORDINATES
    }

    private ship(coordinate, orientation, isSunk) {
        def position = new Position(coordinate: coordinate, orientation: orientation)
        new Ship(position: position, sunk: isSunk)
    }
}