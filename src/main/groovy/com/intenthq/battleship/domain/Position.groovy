package com.intenthq.battleship.domain

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = 'coordinate')
class Position {

    @Delegate Coordinate coordinate
    Orientation orientation

    void rotateLeft() {
        orientation = orientation.rotateLeft()
    }

    void rotateRight() {
        orientation = orientation.rotateRight()
    }

    void next(Dimension dimension) {
        def max = orientation.horizontal ? dimension.width : dimension.height
        coordinate = "next${orientation}Coordinate"(max)
    }
}