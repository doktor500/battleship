package com.intenthq.battleship.domain

import com.intenthq.EqualsHashCodeSpec

class BoardBaseSpec extends EqualsHashCodeSpec {

    @Override
    protected createObjectToCompare() {
        new Board(dimension: new Dimension(0, 0), ships: [shipAt(0, 0)])
    }

    @Override
    protected modifiedPropertiesIncludedInEqualsAndHashCode() {
        [dimension: new Dimension(1, 1), ships: [shipAt(1, 1)]]
    }

    private shipAt(x, y) {
        def position = new Position(coordinate: new Coordinate(x, y))
        new Ship(position: position)
    }
}
