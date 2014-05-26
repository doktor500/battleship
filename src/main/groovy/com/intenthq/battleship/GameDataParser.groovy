package com.intenthq.battleship

import com.intenthq.PatternMatcher
import com.intenthq.battleship.domain.Orientation
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static com.intenthq.Characters.*
import static com.intenthq.utils.VerbalExpression.regex

@Component
class GameDataParser {

    private static final INVALID_GAME_INPUT_DATA = 'Invalid game input data'
    private static final Log log = LogFactory.getLog(this.class);

    @Autowired
    private GameDataValidator gameDataValidator

    GameData process(String data) throws InvalidGameDataException {
        try {
            def gameData = processInput(data)
            gameDataValidator.validate(gameData)
            gameData
        }
        catch (IndexOutOfBoundsException ex) {
            log.error ex.stackTrace
            throw new InvalidGameDataException(INVALID_GAME_INPUT_DATA)
        }
    }

    private processInput(String data) {
        def lines = data.readLines()
        def board = readLine(lines)
        def ships = getShipsLines(readLine(lines))
        def turns = getTurns(lines)
        new GameData(board: board, ships: ships, shipsOperations: turns.shipsOperations, shots: turns.shots)
    }

    private readLine(lines) {
        lines.remove(0)
    }

    private getShipsLines(shipsLine) {
        PatternMatcher.findAll(shipsRegEx, shipsLine)
    }

    private getTurns(lines) {
        def gameTurns = lines.collate(2)
        gameTurns.inject([shipsOperations: [], shots: []]) { turns, turn ->
            turns.shipsOperations << turn.first()
            turns.shots << turn.last()
            turns
        }
    }

    private getShipsRegEx() {
        regex()
        .capture()
        .then(OPENED_BRACKET)
        .digit().multiple('+')
        .then(COMMA)
        .space()
        .digit().multiple('+')
        .then(COMMA)
        .space()
        .anyOf(Orientation.symbols)
        .then(CLOSED_BRACKET)
        .endCapture()
        .build()
        .toString()
    }
}
