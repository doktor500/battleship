package com.intenthq.battleship.domain

import com.intenthq.battleship.presenter.GamePresenter
import com.intenthq.battleship.validators.GameValidator

class Game implements GameValidator, GamePresenter {

    Board board
    List shipOperationCommands
    List shotCommands

    void play() {
        def size = shipOperationCommands.size()
        size.times {
            getShipOperationCommand(it)?.execute()
            getShotCommand(it)?.execute()
        }
    }

    private getShipOperationCommand(index) {
        shipOperationCommands.get(index)
    }

    private getShotCommand(index) {
        shotCommands.get(index)
    }
}
