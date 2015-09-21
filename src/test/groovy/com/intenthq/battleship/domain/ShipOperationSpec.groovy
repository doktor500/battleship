package com.intenthq.battleship.domain

import spock.lang.Specification
import spock.lang.Unroll

import static com.intenthq.battleship.domain.ShipOperation.*

class ShipOperationSpec extends Specification {

    void 'returns all ship operation symbols'() {
        expect:
        ShipOperation.symbols == 'LRM'
    }

    @Unroll
    void 'returns ship operation from a symbol'() {
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
    void 'returns ship operation string value'() {
        expect:
        shipOperation.toString() == shipOperationName

        where:
        shipOperation | shipOperationName
        ROTATE_LEFT   | 'rotateLeft'
        ROTATE_RIGHT  | 'rotateRight'
        MOVE          | 'move'
    }
}
