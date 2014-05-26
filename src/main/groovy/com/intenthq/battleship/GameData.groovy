package com.intenthq.battleship

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class GameData {

    String board
    List ships
    List shipsOperations
    List shots
}
