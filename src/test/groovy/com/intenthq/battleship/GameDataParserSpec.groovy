package com.intenthq.battleship

import spock.lang.Specification

import java.lang.Void as Should

import static com.intenthq.Characters.NEW_LINE

class GameDataParserSpec extends Specification {

    private static final INVALID_GAME_INPUT_DATA = 'Invalid game input data'

    def gameDataParser
    def gameDataValidator

    def setup() {
        gameDataValidator = Mock(GameDataValidator)
        gameDataParser = new GameDataParser(gameDataValidator: gameDataValidator)
    }

    Should "throw an exception when data can't be parsed"() {
        given:
        def data = ''

        when:
        gameDataParser.process(data)

        then:
        def exception = thrown(InvalidGameDataException)
        exception.message == INVALID_GAME_INPUT_DATA
        0 * _
    }

    Should 'return a game data instance when valid game data can be parsed'() {
        given:
        def data = """
            (5, 5)
            (1, 2, N) (3, 3, E)
            (1, 2) LMLMLMLMM
            (2, 3)
            (3, 3) MRMMRMRRM
            (1, 3)
        """

        def expectedGameData = new GameData(
            board: '(5, 5)',
            ships: ['(1, 2, N)', '(3, 3, E)'],
            shipsOperations: ['(1, 2) LMLMLMLMM', '(3, 3) MRMMRMRRM'],
            shots: ['(2, 3)', '(1, 3)']
        )

        when:
        def gameData = gameDataParser.process(format(data))

        then:
        1 * gameDataValidator.validate(expectedGameData)
        gameData == expectedGameData
    }

    private format(String text) {
        text.readLines().tail()*.trim().join(NEW_LINE)
    }
}
