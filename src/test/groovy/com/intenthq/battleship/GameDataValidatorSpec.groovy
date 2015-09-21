package com.intenthq.battleship

import spock.lang.Specification

class GameDataValidatorSpec extends Specification {

    private static final VALID_DATA = 'Valid data'
    private static final IN_VALID_DATA = 'Invalid data'

    private static final INVALID_BOARD = 'Invalid board format at board'
    private static final INVALID_SHIP = 'Invalid ship format at ship'
    private static final INVALID_SHIP_OPERATION = 'Invalid ship operation format at ship operation'
    private static final INVALID_SHOT = 'Invalid shot format at shot'

    def gameDataValidator
    def gameDataItemValidator

    void setup() {
        gameDataItemValidator = Mock(GameDataItemValidator)
        gameDataValidator = new GameDataValidator(gameDataItemValidator: gameDataItemValidator)
    }

    void 'throws an invalid game data exception when there is an invalid board'() {
        given:
        def board = VALID_DATA
        def gameData = new GameData(board: board)

        when:
        gameDataValidator.validate(gameData)

        then:
        1 * gameDataItemValidator.isValidCoordinate(board) >> false
        0 * _

        def exception = thrown(InvalidGameDataException)
        exception.message == "$INVALID_BOARD 0"
    }

    void 'throws an invalid game data exception when there is an invalid ship'() {
        given:
        def board = VALID_DATA
        def ships = [VALID_DATA, IN_VALID_DATA]
        def gameData = new GameData(board: VALID_DATA, ships: ships)

        when:
        gameDataValidator.validate(gameData)

        then:
        1 * gameDataItemValidator.isValidCoordinate(board) >> true
        1 * gameDataItemValidator.isValidShip(ships.first()) >> true
        1 * gameDataItemValidator.isValidShip(ships.last()) >> false
        0 * _

        def exception = thrown(InvalidGameDataException)
        exception.message == "$INVALID_SHIP 1"
    }

    void 'throws an invalid game data exception when there is an invalid ship operation'() {
        given:
        def board = VALID_DATA
        def ships = [VALID_DATA, VALID_DATA]
        def shipsOperations = [VALID_DATA, IN_VALID_DATA]
        def gameData = new GameData(board: board, ships: ships, shipsOperations: shipsOperations)

        when:
        gameDataValidator.validate(gameData)

        then:
        1 * gameDataItemValidator.isValidCoordinate(board) >> true
        1 * gameDataItemValidator.isValidShip(ships.first()) >> true
        1 * gameDataItemValidator.isValidShip(ships.last()) >> true
        1 * gameDataItemValidator.isValidShipOperation(shipsOperations.first()) >> true
        1 * gameDataItemValidator.isValidShipOperation(shipsOperations.last()) >> false
        0 * _

        def exception = thrown(InvalidGameDataException)
        exception.message == "$INVALID_SHIP_OPERATION 1"
    }

    void 'throws an invalid game data exception when there is an invalid shoot'() {
        given:
        def board = VALID_DATA
        def ships = [VALID_DATA, VALID_DATA]
        def shipsOperations = [VALID_DATA, IN_VALID_DATA]
        def shots = [VALID_DATA, IN_VALID_DATA]
        def gameData = new GameData(board: board, ships: ships, shipsOperations: shipsOperations, shots: shots)

        when:
        gameDataValidator.validate(gameData)

        then:
        1 * gameDataItemValidator.isValidCoordinate(board) >> true
        1 * gameDataItemValidator.isValidShip(ships.first()) >> true
        1 * gameDataItemValidator.isValidShip(ships.last()) >> true
        1 * gameDataItemValidator.isValidShipOperation(shipsOperations.first()) >> true
        1 * gameDataItemValidator.isValidShipOperation(shipsOperations.last()) >> true
        1 * gameDataItemValidator.isValidCoordinate(shots.first()) >> true
        1 * gameDataItemValidator.isValidCoordinate(shots.last()) >> false
        0 * _

        def exception = thrown(InvalidGameDataException)
        exception.message == "$INVALID_SHOT 1"
    }

    void 'does not thrown an invalid game data exception when game data is valid'() {
        given:
        def board = VALID_DATA
        def ships = [VALID_DATA, VALID_DATA]
        def shipsOperations = [VALID_DATA, VALID_DATA]
        def shots = [VALID_DATA, VALID_DATA]
        def gameData = new GameData(board: board, ships: ships, shipsOperations: shipsOperations, shots: shots)

        when:
        gameDataValidator.validate(gameData)

        then:
        1 * gameDataItemValidator.isValidCoordinate(board) >> true
        1 * gameDataItemValidator.isValidShip(ships.first()) >> true
        1 * gameDataItemValidator.isValidShip(ships.last()) >> true
        1 * gameDataItemValidator.isValidShipOperation(shipsOperations.first()) >> true
        1 * gameDataItemValidator.isValidShipOperation(shipsOperations.last()) >> true
        1 * gameDataItemValidator.isValidCoordinate(shots.first()) >> true
        1 * gameDataItemValidator.isValidCoordinate(shots.last()) >> true
        0 * _

        notThrown(InvalidGameDataException)
    }
}
