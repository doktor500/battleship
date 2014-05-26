package com.intenthq.battleship.domain

import com.intenthq.EqualsHashCodeSpec

class DimensionBaseSpec extends EqualsHashCodeSpec {

    @Override
    protected createObjectToCompare() {
        new Dimension(0, 0)
    }

    @Override
    protected modifiedPropertiesIncludedInEqualsAndHashCode() {
        [width: 1, height: 1]
    }
}
