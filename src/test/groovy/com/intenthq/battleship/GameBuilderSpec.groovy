package com.intenthq.battleship

import com.intenthq.battleship.command.ShipOperationCmd
import com.intenthq.battleship.command.ShotCmd
import com.intenthq.battleship.domain.Board
import com.intenthq.battleship.domain.Game
import spock.lang.Specification

import java.lang.Void as Should

class GameBuilderSpec extends Specification {

    def gameItemBuilder = Mock(GameItemBuilder)
    def gameBuilder = new GameBuilder(gameItemBuilder: gameItemBuilder)

    Should 'create a game instance'() {
        given:
        def gameData = Mock(GameData)
        def board = Mock(Board), shipOperationCmd = Mock(ShipOperationCmd), shotCmd = Mock(ShotCmd)

        when:
        def currentGame = gameBuilder.create(gameData)

        then:
        1 * gameItemBuilder.createBoard(gameData.board, gameData.ships) >> board
        1 * gameItemBuilder.createShipOperationCommands(board, gameData.shipsOperations) >> [shipOperationCmd]
        1 * gameItemBuilder.createShotCommands(board, gameData.shots) >> [shotCmd]
        currentGame == new Game(board: board, shipOperationCommands: [shipOperationCmd], shotCommands: [shotCmd])
    }
}
