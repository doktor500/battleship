package com.intenthq.battleship.command

import com.intenthq.battleship.domain.Board
import com.intenthq.battleship.domain.Coordinate

class ShotCmd {

    Board board
    Coordinate coordinate

    void execute() {
        board.shoot(coordinate)
    }
}
