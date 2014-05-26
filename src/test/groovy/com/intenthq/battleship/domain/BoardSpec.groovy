package com.intenthq.battleship.domain

import spock.lang.Specification

import java.lang.Void as Should

import static com.intenthq.Characters.NEW_LINE
import static com.intenthq.battleship.domain.Orientation.EAST
import static com.intenthq.battleship.domain.Orientation.NORTH

class BoardSpec extends Specification {

    private static final SHIP_NOT_FOUND = 'Wrong ship coordinates'
    private static final SHIPS_AT_SAME_POSITION = 'The are ships at the same position'

    Board board

    def setup() {
        board = new Board()
    }

    Should 'mark ship as sunk if ship is found when executing a shoot'() {
        given:
        def (ship1, ship2, ship3) = [Mock(Ship), Mock(Ship), Mock(Ship)]
        def coordinate = Mock(Coordinate)
        board.ships = [ship1, ship2, ship3]

        when:
        board.shoot(coordinate)

        then:
        1 * ship1.isAt(coordinate) >> false
        1 * ship2.isAt(coordinate) >> true
        0 * ship3.isAt(coordinate)

        and:
        1 * ship2.markAsSunk()
        0 * _
    }

    Should 'not mark any ship as sunk if no ship is found when executing a shoot'() {
        given:
        def (ship1, ship2, ship3) = [Mock(Ship), Mock(Ship), Mock(Ship)]
        def coordinate = Mock(Coordinate)
        board.ships = [ship1, ship2, ship3]

        when:
        board.shoot(coordinate)

        then:
        1 * ship1.isAt(coordinate) >> false
        1 * ship2.isAt(coordinate) >> false
        1 * ship3.isAt(coordinate) >> false

        and:
        0 * _
    }

    Should 'find a ship by its coordinate'() {
        given:
        def (coordinate1, coordinate2, coordinate3) = [new Coordinate(0, 0), new Coordinate(1, 3), new Coordinate(5, 3)]
        def (ship1, ship2, ship3) = [ship(coordinate1), ship(coordinate2), ship(coordinate3)]
        def board = new Board(ships: [ship1, ship2, ship3])

        expect:
        board.findShip(coordinate1) == ship1
        board.findShip(coordinate2) == ship2
        board.findShip(coordinate3) == ship3
    }

    Should 'throw an invalid game exception if a ship is not found'() {
        given:
        def coordinate = Mock(Coordinate)
        def ship = Mock(Ship)
        def board = new Board(ships: [ship])

        when:
        board.findShip(coordinate)

        then:
        1 * ship.isAt(coordinate) >> false
        def exception = thrown(InvalidGameException)
        exception.message == SHIP_NOT_FOUND
    }

    Should 'return board dimensions'() {
        given:
        def (boardWidth, boardHeight) = [3, 5]
        board.dimension = new Dimension(boardWidth, boardHeight)

        expect:
        board.width == boardWidth
        board.height == boardHeight
    }

    Should 'return ships status'() {
        given:
        def (firstShip, secondShip) = [ship(new Coordinate(0, 0), NORTH, false), ship(new Coordinate(1, 2), EAST, true)]
        def board = new Board(ships: [firstShip, secondShip])

        expect:
        board.format() == '(0, 0, N) ' + NEW_LINE + '(1, 2, E) SUNK'
    }

    Should 'return if the board is valid'() {
        given:
        def board = new Board(dimension: new Dimension(1, 2))

        expect:
        board.validate()
    }

    Should 'throw an invalid game exception if there are ships at the same position'() {
        given:
        def (ship1, ship2) = [ship(new Coordinate(2, 2)), ship(new Coordinate(2, 2))]
        def board = new Board(dimension: new Dimension(2, 5), ships: [ship1, ship2])
        ship1.board = board
        ship2.board = board

        when:
        board.validate()

        then:
        def exception = thrown(InvalidGameException)
        exception.message == SHIPS_AT_SAME_POSITION
    }

    private ship(coordinate, orientation = null, isSunk = false) {
        def position = new Position(coordinate: coordinate, orientation: orientation)
        new Ship(position: position, sunk: isSunk)
    }
}
