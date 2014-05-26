package com.intenthq.battleship

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GameDataValidator {

    private static final INVALID_BOARD = 'Invalid board format at board'
    private static final INVALID_SHIP = 'Invalid ship format at ship'
    private static final INVALID_SHIP_OPERATION = 'Invalid ship operation format at ship operation'
    private static final INVALID_SHOT = 'Invalid shot format at shot'

    @Autowired
    private GameDataItemValidator gameDataItemValidator

    void validate(GameData gameData) throws InvalidGameDataException {
        validators.each { validator -> validator(gameData) }
    }

    private getValidators() {
        [validateBoard, validateShips, validateShipOperations, validateShots]
    }

    private validateBoard = { GameData gameData ->
        validate('isValidCoordinate', INVALID_BOARD, gameData.board)
    }

    private validateShips = { GameData gameData ->
        validate('isValidShip', INVALID_SHIP, *gameData.ships)
    }

    private validateShipOperations = { GameData gameData ->
        validate('isValidShipOperation', INVALID_SHIP_OPERATION, *gameData.shipsOperations)
    }

    private validateShots = { GameData gameData ->
        validate('isValidCoordinate', INVALID_SHOT, *gameData.shots)
    }

    private validate = { String method, String message, ...items ->
        items.eachWithIndex { item, index ->
            gameDataItemValidator."$method"(item) ?: invalidGameDataException("$message $index")
        }
    }

    private invalidGameDataException(String message) {
        throw new InvalidGameDataException(message)
    }
}