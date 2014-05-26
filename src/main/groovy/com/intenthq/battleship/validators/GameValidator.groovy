package com.intenthq.battleship.validators

trait GameValidator {

    boolean validate() {
        board.validate()
    }
}
