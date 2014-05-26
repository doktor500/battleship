package com.intenthq.battleship

import com.intenthq.battleship.command.ShipOperationCmd
import com.intenthq.battleship.command.ShotCmd
import com.intenthq.battleship.domain.*
import org.springframework.stereotype.Component

import static com.intenthq.Characters.*
import static com.intenthq.utils.VerbalExpression.regex

@Component
class GameItemBuilder {

    Board createBoard(String boardLine, List shipsLines) {
        def (width, height) = getContent(boardLine)
        def dimension = new Dimension(width, height)
        def board = new Board(dimension: dimension)
        def ships = createShips(board, shipsLines)
        board.ships = ships
        board
    }

    List createShipOperationCommands(Board board, List shipsOperations) {
        shipsOperations.collect {
            def (x, y, moves) = getContent(it)
            def coordinate = new Coordinate(x, y)
            def shipOperations = moves.collect { ShipOperation.fromSymbol(it) }
            new ShipOperationCmd(board: board, coordinate: coordinate, shipOperations: shipOperations)
        }
    }

    List createShotCommands(Board board, List shoot) {
        shoot.collect {
            def (x, y) = getContent(it)
            def coordinate = new Coordinate(x, y)
            new ShotCmd(board: board, coordinate: coordinate)
        }
    }

    private createShips(Board board, List shipsLines) {
        shipsLines.collect { createShip(board, it) }
    }

    private createShip(board, ship) {
        def (x, y, direction) = getContent(ship)
        def coordinate = new Coordinate(x, y)
        def orientation = Orientation.fromSymbol(direction)
        def position = new Position(coordinate: coordinate, orientation: orientation)
        new Ship(board: board, position: position)
    }

    private getContent(line) {
        line.replaceAll(contentRegEx, EMPTY).split(SPACE)
    }

    private getContentRegEx() {
        regex()
        .anyOf(delimiters)
        .build()
        .toString()
    }
}
