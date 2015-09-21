package com.intenthq.battleship.controller

import com.intenthq.battleship.GameBuilder
import com.intenthq.battleship.GameData
import com.intenthq.battleship.GameDataParser
import com.intenthq.battleship.InvalidGameDataException
import com.intenthq.battleship.domain.Game
import com.intenthq.battleship.domain.InvalidGameException
import org.springframework.ui.ModelMap
import spock.lang.Specification

class BattleShipControllerSpec extends Specification {

    private static final EXERCISE_VIEW = 'exercise'
    private static final BATTLESHIP_VIEW = 'battleship'

    def gameDataParser
    def gameBuilder
    def battleShipController

    void setup() {
        gameDataParser = Mock(GameDataParser)
        gameBuilder = Mock(GameBuilder)
        battleShipController = new BattleShipController(gameDataParser: gameDataParser, gameBuilder: gameBuilder)
    }

    void 'returns battleship view'() {
        expect:
        battleShipController.battleship() == BATTLESHIP_VIEW
    }

    void 'returns game execution result'() {
        given:
        def (input, output) = ['valid game input data', 'game result']
        def gameData = Mock(GameData)
        def game = Mock(Game)
        def model = Mock(ModelMap)

        when:
        def view = battleShipController.exercise(input, model)

        then:
        1 * gameDataParser.process(input) >> gameData
        1 * gameBuilder.create(gameData) >> game
        1 * game.play()
        1 * game.validate()
        1 * game.format() >> output
        1 * model.addAttribute('output', output)
        view == EXERCISE_VIEW
    }

    void 'returns exercise view when there is no input data'() {
        given:
        def input = ''
        def model = Mock(ModelMap)

        when:
        def view = battleShipController.exercise(input, model)

        then:
        0 * _
        view == EXERCISE_VIEW
    }

    void 'returns the exception message if an invalid game data exception occurs'() {
        given:
        def (input, exceptionMessage) = ['invalid game input data', 'error occurred']
        def model = Mock(ModelMap)

        when:
        battleShipController.exercise(input, model)

        then:
        1 * gameDataParser.process(input) >> { invalidGameDataException(exceptionMessage) }
        1 * model.addAttribute('error', exceptionMessage)
    }

    void 'returns the exception message if an invalid game exception occurs'() {
        given:
        def gameData = Mock(GameData)
        def game = Mock(Game)
        def (input, exceptionMessage) = ['invalid game input data', 'error occurred']
        def model = Mock(ModelMap)

        when:
        battleShipController.exercise(input, model)

        then:
        1 * gameDataParser.process(input) >> gameData
        1 * gameBuilder.create(gameData) >> game
        1 * game.validate() >> { invalidGameException(exceptionMessage) }
        1 * model.addAttribute('error', exceptionMessage)
    }

    private invalidGameDataException(exceptionMessage) {
        throw new InvalidGameDataException(exceptionMessage)
    }

    private invalidGameException(exceptionMessage) {
        throw new InvalidGameException(exceptionMessage)
    }
}
