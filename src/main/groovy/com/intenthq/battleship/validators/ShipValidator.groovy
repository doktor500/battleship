package com.intenthq.battleship.validators

import com.intenthq.battleship.domain.InvalidGameException

trait ShipValidator {

    private static final INVALID_SHIP_COORDINATES = 'The ship must have a coordinate inside the board'

    boolean validate() {
        isShipInsideBoard() ?: invalidGameException(INVALID_SHIP_COORDINATES)
    }

    private isShipInsideBoard() {
        position.x <= board.width && position.y <= board.height
    }

    private invalidGameException(message) {
        throw new InvalidGameException(message)
    }
}
