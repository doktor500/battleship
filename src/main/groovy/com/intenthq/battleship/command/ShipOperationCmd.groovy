package com.intenthq.battleship.command

import com.intenthq.battleship.domain.Board
import com.intenthq.battleship.domain.Coordinate

class ShipOperationCmd {

    Board board
    Coordinate coordinate
    List shipOperations

    void execute() {
        def ship = board.findShip(coordinate)
        shipOperations.each { ship."${it}"() }
        board.validate()
    }
}
