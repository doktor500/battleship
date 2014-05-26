package com.intenthq.battleship.validators

import com.intenthq.battleship.domain.InvalidGameException

trait BoardValidator {

    private static final SHIPS_AT_SAME_POSITION = 'The are ships at the same position'

    boolean validate() {
        areShipsAtDifferentPositions() ?: invalidGameException(SHIPS_AT_SAME_POSITION)
        ships*.validate()
        true
    }

    private areShipsAtDifferentPositions() {
        ships.size() == ships.toSet().size()
    }

    private invalidGameException(message) {
        throw new InvalidGameException(message)
    }
}
