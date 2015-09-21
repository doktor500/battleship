package com.intenthq.battleship

import com.intenthq.battleship.command.ShipOperationCmd
import com.intenthq.battleship.command.ShotCmd
import com.intenthq.battleship.domain.Board
import com.intenthq.battleship.domain.Game
import spock.lang.Specification

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals

class GameBuilderSpec extends Specification {

    def gameItemBuilder
    def gameBuilder

    void setup() {
        gameItemBuilder = Mock(GameItemBuilder)
        gameBuilder = new GameBuilder(gameItemBuilder: gameItemBuilder)
    }

    void 'creates a game instance'() {
        given:
        def gameData = Mock(GameData)
        def board = Mock(Board), shipOperationCmd = Mock(ShipOperationCmd), shotCmd = Mock(ShotCmd)

        when:
        def currentGame = gameBuilder.create(gameData)

        then:
        1 * gameItemBuilder.createBoard(gameData.board, gameData.ships) >> board
        1 * gameItemBuilder.createShipOperationCommands(board, gameData.shipsOperations) >> [shipOperationCmd]
        1 * gameItemBuilder.createShotCommands(board, gameData.shots) >> [shotCmd]
        assertReflectionEquals(
            currentGame,
            new Game(board: board, shipOperationCommands: [shipOperationCmd], shotCommands: [shotCmd])
        )
    }
}
