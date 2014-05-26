package com.intenthq.battleship.domain

import com.intenthq.battleship.presenter.BoardPresenter
import com.intenthq.battleship.validators.BoardValidator
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Board implements BoardValidator, BoardPresenter {

    private static final SHIP_NOT_FOUND = 'Wrong ship coordinates'

    @Delegate Dimension dimension
    List ships = []

    void shoot(Coordinate coordinate) throws InvalidGameException {
        locateShip(coordinate)?.markAsSunk()
    }

    Ship findShip(coordinate) throws InvalidGameException {
        def ship = locateShip(coordinate)
        if (!ship) { throw new InvalidGameException(SHIP_NOT_FOUND) }
        ship
    }

    private locateShip(coordinate) {
        ships.find { ship -> ship.isAt(coordinate) }
    }
}