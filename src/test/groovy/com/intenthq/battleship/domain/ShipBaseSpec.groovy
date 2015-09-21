package com.intenthq.battleship.domain

import com.intenthq.EqualsHashCodeSpec

class ShipBaseSpec extends EqualsHashCodeSpec {

    @Override
    protected createObjectToCompare() {
        new Ship(position: shipAtPosition(0, 0))
    }

    @Override
    protected modifiedPropertiesIncludedInEqualsAndHashCode() {
        [position: shipAtPosition(1, 1)]
    }

    def shipAtPosition(x, y) {
        new Position(coordinate: new Coordinate(x, y))
    }
}
