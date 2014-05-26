package com.intenthq.battleship.domain

import com.intenthq.EqualsHashCodeSpec

class PositionBaseSpec extends EqualsHashCodeSpec {

    @Override
    protected createObjectToCompare() {
        new Position(coordinate: new Coordinate(0, 0))
    }

    @Override
    protected modifiedPropertiesIncludedInEqualsAndHashCode() {
        [coordinate: new Coordinate(1, 1)]
    }
}
