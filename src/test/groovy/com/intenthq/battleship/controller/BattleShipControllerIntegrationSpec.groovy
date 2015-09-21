package com.intenthq.battleship.controller

import com.intenthq.battleship.Application
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.ui.ModelMap
import spock.lang.Specification

import static com.intenthq.Characters.NEW_LINE

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = Application)
class BattleShipControllerIntegrationSpec extends Specification {

    private static final INVALID_GAME_INPUT_DATA = 'Invalid game input data'
    private static final INVALID_BOARD = 'Invalid board format at board 0'
    private static final INVALID_SHIP = 'Wrong ship coordinates'
    private static final INVALID_SHIP_OPERATION = 'Invalid ship operation format at ship operation 0'
    private static final SHIPS_AT_SAME_POSITION = 'The are ships at the same position'
    private static final INVALID_SHIP_COORDINATES = 'The ship must have a coordinate inside the board'
    private static final INVALID_SHOT = 'Invalid shot format at shot 1'

    @Autowired private BattleShipController battleShipController

    @Test()
    void 'returns expected game output'() {
        given:
        def modelMap = new ModelMap()
        def input = """
            (5, 5)
            (1, 2, N) (3, 3, E)
            (1, 2) LMLMLMLMM
            (2, 3)
            (3, 3) MRMMRMRRM
            (1, 3)
        """

        def output = """
            (1, 3, N) SUNK
            (4, 1, E)
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.output.trim() == format(output).trim()
    }

    @Test()
    void 'returns every ship as sunk'() {
        given:
        def modelMap = new ModelMap()
        def input = """
            (5, 5)
            (1, 2, N) (3, 3, E) (5, 5, S)
            (1, 2) LMLMLMLMM
            (1, 3)
            (3, 3) MRMMRMRRM
            (4, 1)
            (5, 5) LLMRLMRRML
            (5, 0)
        """

        def output = """
            (1, 3, N) SUNK
            (4, 1, E) SUNK
            (5, 0, E) SUNK
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.output.trim() == format(output).trim()
    }

    @Test()
    void 'returns an error when invalid input data'() {
        given:
        def modelMap = new ModelMap()
        def input = '---'

        when:
        battleShipController.exercise(input, modelMap)

        then:
        modelMap.error == INVALID_GAME_INPUT_DATA
    }

    @Test()
    void 'returns an error when invalid board data'() {
        given:
        def modelMap = new ModelMap()
        def input = """
            (5, S)
            (1, 2, N) (3, 3, E)
            (1, 2) LMLMLMLMM
            (2, 3)
            (3, 3) MRMMRMRRM
            (1, 3)
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.error == INVALID_BOARD
    }

    @Test
    void 'returns an error when board dimension is invalid'() {
        def modelMap = new ModelMap()
        def input = """
            (-1, 5)
            (1, 2, N) (1, 2, E)
            (1, 2) LMLMLMLMM
            (2, 3)
            (3, 3) MRMMRMRRM
            (1, 3)
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.error == INVALID_BOARD
    }

    @Test()
    void 'returns an error when invalid ship data'() {
        given:
        def modelMap = new ModelMap()
        def input = """
            (5, 7)
            (1, X, N) (3, 3, E)
            (1, 2) LMLMLMLMM
            (2, 3)
            (3, 3) MRMMRMRRM
            (1, 3)
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.error == INVALID_SHIP
    }

    @Test()
    void 'returns an error when invalid ship operation data'() {
        given:
        def modelMap = new ModelMap()
        def input = """
            (5, 7)
            (1, 2, N) (3, 3, E)
            (1, X) LMLMLMLMM
            (2, 3)
            (3, 3) MRMMRMRRM
            (1, 3)
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.error == INVALID_SHIP_OPERATION
    }

    @Test()
    void 'returns an error when invalid shot data'() {
        given:
        def modelMap = new ModelMap()
        def input = """
            (5, 7)
            (1, 2, N) (3, 3, E)
            (1, 2) LMLMLMLMM
            (2, 1)
            (3, 3) MRMMRMRRM
            (1, X)
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.error == INVALID_SHOT
    }

    @Test
    void 'returns an error when there are ships on the same position'() {
        def modelMap = new ModelMap()
        def input = """
            (5, 5)
            (1, 2, N) (1, 2, E)
            (1, 2) LMLMLMLMM
            (2, 3)
            (3, 3) MRMMRMRRM
            (1, 3)
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.error == SHIPS_AT_SAME_POSITION
    }

    @Test
    void 'returns an error when there are ships out of the board'() {
        def modelMap = new ModelMap()
        def input = """
            (5, 5)
            (1, 2, N) (7, 2, E)
            (1, 2) LMLMLMLMM
            (2, 3)
            (3, 3) MRMMRMRRM
            (1, 3)
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.error == INVALID_SHIP_COORDINATES
    }

    @Test
    void 'returns an error because ship movement does not reference a ship'() {
        def modelMap = new ModelMap()
        def input = """
            (5, 5)
            (1, 2, N) (3, 2, E)
            (4, 2) LMLMLMLMM
            (2, 3)
            (3, 3) MRMMRMRRM
            (1, 3)
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.error == INVALID_SHIP
    }

    @Test()
    void 'returns an error when invalid turn data'() {
        given:
        def modelMap = new ModelMap()
        def input = """
            (5, 5)
            (1, 2, N) (3, 3, E)
            (1, 2) LMLMLMLMM
            (2, 3)
            (3, 3) MRMMRMRRM
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.error == INVALID_SHOT
    }

    @Test
    void 'returns an error because a ship is in the same position as other after it moves'() {
        def modelMap = new ModelMap()
        def input = """
            (5, 5)
            (2, 2, N) (3, 3, N)
            (2, 2) LL
            (2, 3)
            (3, 3) LMLM
            (1, 3)
        """

        when:
        battleShipController.exercise(format(input), modelMap)

        then:
        modelMap.error == SHIPS_AT_SAME_POSITION
    }

    private format(String text) {
        text.readLines().tail()*.trim().join(NEW_LINE)
    }
}
