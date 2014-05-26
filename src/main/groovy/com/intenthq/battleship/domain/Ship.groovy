package com.intenthq.battleship.domain

import com.intenthq.battleship.presenter.ShipPresenter
import com.intenthq.battleship.validators.ShipValidator
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = ['position'])
class Ship implements ShipValidator, ShipPresenter {

    private static final SUNK = 'SUNK'

    @Delegate(includes = ['rotateLeft', 'rotateRight'])
    Position position
    Board board
    boolean sunk

    boolean isAt(Coordinate coordinate) {
        position.coordinate == coordinate
    }

    void markAsSunk() {
        sunk = true
    }

    void move() {
        position.next(board.dimension)
    }
}