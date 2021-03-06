package com.intenthq.battleship.command

import com.intenthq.battleship.domain.Board
import com.intenthq.battleship.domain.Coordinate
import spock.lang.Specification

class ShotCmdSpec extends Specification {

    void 'executes a shot on the board'() {
        given:
        def board = Mock(Board)
        def coordinate = Mock(Coordinate)
        def shotCmd = new ShotCmd(board: board, coordinate: coordinate)

        when:
        shotCmd.execute()

        then:
        1 * board.shoot(coordinate)
    }
}
