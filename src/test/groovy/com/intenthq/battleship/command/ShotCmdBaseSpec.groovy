package com.intenthq.battleship.command

import com.intenthq.EqualsHashCodeSpec
import com.intenthq.battleship.domain.Board
import com.intenthq.battleship.domain.Coordinate
import com.intenthq.battleship.domain.Position
import com.intenthq.battleship.domain.Ship

class ShotCmdBaseSpec extends EqualsHashCodeSpec {

    @Override
    protected createObjectToCompare() {
        new ShotCmd(board: boardWithShipAt(0, 0), coordinate: new Coordinate(0, 0))
    }

    @Override
    protected modifiedPropertiesIncludedInEqualsAndHashCode() {
        [board: boardWithShipAt(1, 1), coordinate: new Coordinate(1, 1)]
    }

    private boardWithShipAt(x, y) {
        def position = new Position(coordinate: new Coordinate(x, y))
        def ship = new Ship(position: position)
        new Board(ships: [ship])
    }
}
