package com.intenthq.battleship

import com.intenthq.battleship.domain.Game
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GameBuilder {

    @Autowired
    private GameItemBuilder gameItemBuilder

    Game create(GameData gameData) {
        def board = gameItemBuilder.createBoard(gameData.board, gameData.ships)
        def shipOperationCommands = gameItemBuilder.createShipOperationCommands(board, gameData.shipsOperations)
        def shotsCommands = gameItemBuilder.createShotCommands(board, gameData.shots)
        new Game(board: board, shipOperationCommands: shipOperationCommands, shotCommands: shotsCommands)
    }
}
