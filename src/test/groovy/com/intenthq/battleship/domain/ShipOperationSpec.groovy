package com.intenthq.battleship.domain

import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

import static com.intenthq.battleship.domain.ShipOperation.*

class ShipOperationSpec extends Specification {

    Should 'return all ship operation symbols'() {
        expect:
        ShipOperation.symbols == 'LRM'
    }

    @Unroll
    Should 'return ship operation from a symbol'() {
        expect:
        ShipOperation.fromSymbol(symbol) == shipOperation

        where:
        symbol | shipOperation
        'L'    | ROTATE_LEFT
        'R'    | ROTATE_RIGHT
        'M'    | MOVE
        'X'    | null
    }

    @Unroll
    Should 'return ship operation string value'() {
        expect:
        shipOperation.toString() == shipOperationName

        where:
        shipOperation | shipOperationName
        ROTATE_LEFT   | 'rotateLeft'
        ROTATE_RIGHT  | 'rotateRight'
        MOVE          | 'move'
    }
}
