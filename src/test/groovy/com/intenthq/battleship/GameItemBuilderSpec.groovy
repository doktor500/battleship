package com.intenthq.battleship

import com.intenthq.battleship.command.ShipOperationCmd
import com.intenthq.battleship.command.ShotCmd
import com.intenthq.battleship.domain.*
import spock.lang.Specification

import static com.intenthq.battleship.domain.Orientation.EAST
import static com.intenthq.battleship.domain.Orientation.NORTH
import static com.intenthq.battleship.domain.ShipOperation.*
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals

class GameItemBuilderSpec extends Specification {

    def gameItemBuilder

    void setup() {
        gameItemBuilder = new GameItemBuilder()
    }

    void 'creates game board'() {
        given:
        def boardLine = '(5, 5)'
        def shipsLines = ['(1, 2, N)', '(3, 3, E)']

        def board = createBoard(5, 5)
        def ships = [createShip(1, 2, NORTH), createShip(3, 3, EAST)]
        link(board, ships)

        expect:
        assertReflectionEquals(gameItemBuilder.createBoard(boardLine, shipsLines), board)
    }

    void 'creates ship operation commands'() {
        given:
        def board = new Board()
        def shipOperationLines = ['(1, 2) LM', '(3, 3) MR']
        def (firstCoordinate, secondCoordinate) = [new Coordinate(1, 2), new Coordinate(3, 3)]
        def firstShipOperation = createShipOperationCmd(board, firstCoordinate, ROTATE_LEFT, MOVE)
        def secondShipOperation = createShipOperationCmd(board, secondCoordinate, MOVE, ROTATE_RIGHT)

        expect:
        assertReflectionEquals(
            gameItemBuilder.createShipOperationCommands(board, shipOperationLines),
            [firstShipOperation, secondShipOperation]
        )
    }

    void 'creates shot commands'() {
        given:
        def board = new Board()
        def shotLines = ['(1, 2)', '(3, 3)']
        def (firstCoordinate, secondCoordinate) = [new Coordinate(1, 2), new Coordinate(3, 3)]
        def firstShot = createShotCmd(board, firstCoordinate)
        def secondShot = createShotCmd(board, secondCoordinate)

        expect:
        assertReflectionEquals(gameItemBuilder.createShotCommands(board, shotLines), [firstShot, secondShot])
    }

    private createBoard(width, height) {
        def dimension = new Dimension(width, height)
        new Board(dimension: dimension)
    }

    private createShip(x, y, orientation) {
        def coordinate = new Coordinate(x, y)
        def position = new Position(coordinate: coordinate, orientation: orientation)
        new Ship(position: position)
    }

    private createShipOperationCmd(board, coordinate, ...shipOperations) {
        new ShipOperationCmd(board: board, coordinate: coordinate, shipOperations: shipOperations.toList())
    }

    private createShotCmd(board, coordinate) {
        new ShotCmd(board: board, coordinate: coordinate)
    }

    private link(board, ships) {
        board.ships = ships
        ships.each { it.board = board }
    }
}
