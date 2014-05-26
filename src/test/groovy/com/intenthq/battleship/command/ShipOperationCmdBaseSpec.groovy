package com.intenthq.battleship.command

import com.intenthq.EqualsHashCodeSpec
import com.intenthq.battleship.domain.Board
import com.intenthq.battleship.domain.Coordinate
import com.intenthq.battleship.domain.Position
import com.intenthq.battleship.domain.Ship

import static com.intenthq.battleship.domain.ShipOperation.ROTATE_LEFT
import static com.intenthq.battleship.domain.ShipOperation.ROTATE_RIGHT

class ShipOperationCmdBaseSpec extends EqualsHashCodeSpec {

    @Override
    protected createObjectToCompare() {
        new ShipOperationCmd(
            board: boardWithShipAt(0, 0),
            coordinate: new Coordinate(0, 0),
            shipOperations: [ROTATE_RIGHT]
        )
    }

    @Override
    protected modifiedPropertiesIncludedInEqualsAndHashCode() {
        [
            board: boardWithShipAt(1, 1),
            coordinate: new Coordinate(1, 1),
            shipOperations: [ROTATE_LEFT]
        ]
    }

    private boardWithShipAt(x, y) {
        def position = new Position(coordinate: new Coordinate(x, y))
        def ship = new Ship(position: position)
        new Board(ships: [ship])
    }
}
