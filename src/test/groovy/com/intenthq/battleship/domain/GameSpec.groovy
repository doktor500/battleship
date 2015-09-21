package com.intenthq.battleship.domain

import com.intenthq.battleship.command.ShipOperationCmd
import com.intenthq.battleship.command.ShotCmd
import spock.lang.Specification

class GameSpec extends Specification {

    void 'executes commands turn by turn'() {
        given:
        def (firstShipOperationCmd, secondShipOperationCmd) = [Mock(ShipOperationCmd), Mock(ShipOperationCmd)]
        def (firstShotCmd, secondShotCmd) = [Mock(ShotCmd), Mock(ShotCmd)]
        def shipOperationCommands = [firstShipOperationCmd, secondShipOperationCmd]
        def shotCommands = [firstShotCmd, secondShotCmd]
        def game = new Game(shipOperationCommands: shipOperationCommands, shotCommands: shotCommands)

        when:
        game.play()

        then:
        interaction {
            1 * firstShipOperationCmd.execute()
            1 * firstShotCmd.execute()

            1 * secondShipOperationCmd.execute()
            1 * secondShotCmd.execute()
        }
    }

    void 'presents game result formatted'() {
        given:
        def boardFormatted = '(1, 3, N) SUNK'
        def board = Mock(Board)
        def game = new Game(board: board)

        when:
        def gameResult = game.format()

        then:
        1 * board.format() >> boardFormatted
        gameResult == boardFormatted
    }

    void 'validates the game'() {
        given:
        def board = Mock(Board)
        def game = new Game(board: board)

        when:
        def isValid = game.validate()

        then:
        board.validate() >> true
        isValid
    }
}
