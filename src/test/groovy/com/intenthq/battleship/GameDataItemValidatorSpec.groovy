package com.intenthq.battleship

import spock.lang.Specification
import spock.lang.Unroll

class GameDataItemValidatorSpec extends Specification {

    def gameDataItemValidator

    void setup() {
        gameDataItemValidator = new GameDataItemValidator()
    }

    @Unroll
    void 'returns if a coordinate can be parsed'() {
        expect:
        gameDataItemValidator.isValidCoordinate(input) == isValid

        where:
        input      | isValid
        '(5, 5)'   | true
        '(50, 50)' | true

        '[5, 5)'   | false
        '(S, 5)'   | false
        '(5. 5)'   | false
        '(5,#5)'   | false
        '(5, X)'   | false
        '(5, 5]'   | false
    }

    @Unroll
    void 'returns if a boat can be parsed'() {
        expect:
        gameDataItemValidator.isValidShip(input) == isValid

        where:
        input         | isValid
        '(1, 2, N)'   | true
        '(5, 3, S)'   | true
        '(3, 3, E)'   | true
        '(1, 5, W)'   | true
        '(50, 50, W)' | true

        '[1, 2, N)'   | false
        '($, 2, N)'   | false
        '(1. 2, N)'   | false
        '(1,$2, N)'   | false
        '(1, $, N)'   | false
        '(1, 2. N)'   | false
        '(1, 2,$N)'   | false
        '(1, 2, n)'   | false
        '(1, 2, N]'   | false
        '(1$, 2, N)'  | false
        '(1$, 2$, N)' | false
    }

    @Unroll
    void 'returns if a movement can be parsed'() {
        expect:
        gameDataItemValidator.isValidShipOperation(input) == isValid

        where:
        input              | isValid
        '(1, 2) LMLMLMLMM' | true
        '(3, 3) LMRMLMRMM' | true
        '(33, 33) LL'      | true

        '[3, 7) LMRMLMRMM' | false
        '($, 7) LMRMLMRMM' | false
        '(3. 7) LMRMLMRMM' | false
        '(3,$7) LMRMLMRMM' | false
        '(3, $) LMRMLMRMM' | false
        '(3, 7] LMRMLMRMM' | false
        '(3, 7)-LMRMLMRMM' | false
        '(3, 7) $'         | false
        '(3$, 7) L'        | false
        '(3, 7$) L'        | false
        '(3, 7) L$'        | false
    }
}
