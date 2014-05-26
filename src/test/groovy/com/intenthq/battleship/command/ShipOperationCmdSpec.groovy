package com.intenthq.battleship.command

import com.intenthq.battleship.domain.Board
import com.intenthq.battleship.domain.Coordinate
import com.intenthq.battleship.domain.Ship
import spock.lang.Specification

import java.lang.Void as Should

import static com.intenthq.battleship.domain.ShipOperation.MOVE
import static com.intenthq.battleship.domain.ShipOperation.ROTATE_RIGHT

class ShipOperationCmdSpec extends Specification {

    Should 'execute the command'() {
        given:
        def board = Mock(Board)
        def ship = Mock(Ship)
        def coordinate = Mock(Coordinate)
        def shipOperations = [ROTATE_RIGHT, MOVE]
        def command = new ShipOperationCmd(board: board, coordinate: coordinate, shipOperations: shipOperations)

        when:
        command.execute()

        then:
        1 * board.findShip(coordinate) >> ship
        1 * ship.rotateRight()
        1 * ship.move()
        1 * board.validate()
    }
}
