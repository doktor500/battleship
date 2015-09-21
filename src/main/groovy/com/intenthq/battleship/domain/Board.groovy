package com.intenthq.battleship.domain

import com.intenthq.battleship.presenter.BoardPresenter
import com.intenthq.battleship.validators.BoardValidator

class Board implements BoardValidator, BoardPresenter {

    private static final SHIP_NOT_FOUND = 'Wrong ship coordinates'

    @Delegate Dimension dimension
    List ships = []

    void shoot(Coordinate coordinate) throws InvalidGameException {
        locateShip(coordinate)?.markAsSunk()
    }

    Ship findShip(coordinate) throws InvalidGameException {
        def ship = locateShip(coordinate)
        ship ?: throwError()
    }

    private throwError() {
        throw new InvalidGameException(SHIP_NOT_FOUND)
    }

    private locateShip(coordinate) {
        ships.find { ship -> ship.isAt(coordinate) }
    }
}
