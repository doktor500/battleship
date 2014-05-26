package com.intenthq.battleship

import com.intenthq.battleship.domain.Orientation
import com.intenthq.battleship.domain.ShipOperation
import org.springframework.stereotype.Component

import static com.intenthq.Characters.*
import static com.intenthq.utils.VerbalExpression.regex

@Component
class GameDataItemValidator {

    boolean isValidCoordinate(String coordinate) {
        coordinateRegexTester().test(coordinate)
    }

    boolean isValidShip(String ship) {
        shipRegexTester().test(ship)
    }

    boolean isValidShipOperation(String shipOperation) {
        shipOperationRegexTester().test(shipOperation)
    }

    private coordinateRegexTester() {
        regex()
        .startOfLine()
        .then(OPENED_BRACKET)
        .digit().multiple('+')
        .then(COMMA)
        .space()
        .digit().multiple('+')
        .then(CLOSED_BRACKET)
        .endOfLine()
        .build()
    }

    private shipRegexTester() {
        regex()
        .startOfLine()
        .then(OPENED_BRACKET)
        .digit().multiple('+')
        .then(COMMA)
        .space()
        .digit().multiple('+')
        .then(COMMA)
        .space()
        .anyOf(Orientation.symbols)
        .then(CLOSED_BRACKET)
        .endOfLine()
        .build()
    }

    private shipOperationRegexTester() {
        regex()
        .startOfLine()
        .then(OPENED_BRACKET)
        .digit().multiple('+')
        .then(COMMA)
        .space()
        .digit().multiple('+')
        .then(CLOSED_BRACKET)
        .space()
        .anyOf(ShipOperation.symbols).multiple('+')
        .endOfLine()
        .build()
    }
}