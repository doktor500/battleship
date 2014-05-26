package com.intenthq.battleship

import com.intenthq.EqualsHashCodeSpec

class GameDataBaseSpec extends EqualsHashCodeSpec {

    @Override
    protected createObjectToCompare() {
        new GameData(
            board: 'board',
            ships: ['ships'],
            shipsOperations: ['ships operations'],
            shots: ['shots']
        )
    }

    @Override
    protected modifiedPropertiesIncludedInEqualsAndHashCode() {
        [
            board: 'other board',
            ships: ['other ships'],
            shipsOperations: ['other ships operations'],
            shots: ['other shots']
        ]
    }
}
