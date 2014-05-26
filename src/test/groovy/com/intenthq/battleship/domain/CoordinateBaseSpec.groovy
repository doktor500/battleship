package com.intenthq.battleship.domain

import com.intenthq.EqualsHashCodeSpec

class CoordinateBaseSpec extends EqualsHashCodeSpec {

    @Override
    protected createObjectToCompare() {
        new Coordinate(0, 0)
    }

    @Override
    protected modifiedPropertiesIncludedInEqualsAndHashCode() {
        [x: 1, y: 1]
    }
}