package com.intenthq.battleship.domain

import com.intenthq.EqualsHashCodeSpec
import com.intenthq.battleship.command.ShipOperationCmd
import com.intenthq.battleship.command.ShotCmd

class GameBaseSpec extends EqualsHashCodeSpec {

    @Override
    protected createObjectToCompare() {
        new Game(
            board: new Board(ships: [shipAt(0, 0)]),
            shipOperationCommands: [shipOperationCommand(0, 0)],
            shotCommands: [shotCommand(0, 0)]
        )
    }

    @Override
    protected modifiedPropertiesIncludedInEqualsAndHashCode() {
        [
            board: new Board(ships: [shipAt(1, 1)]),
            shipOperationCommands: [shipOperationCommand(1, 1)],
            shotCommands: [shotCommand(1, 1)]
        ]
    }

    private shipAt(x, y) {
        def position = new Position(coordinate: new Coordinate(x, y))
        new Ship(position: position)
    }

    private shipOperationCommand(x, y) {
        new ShipOperationCmd(coordinate: new Coordinate(x, y))
    }

    private shotCommand(x, y) {
        new ShotCmd(coordinate: new Coordinate(x, y))
    }
}
