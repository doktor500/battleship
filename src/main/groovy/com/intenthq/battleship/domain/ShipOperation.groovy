package com.intenthq.battleship.domain

import com.intenthq.StringUtils

enum ShipOperation {

    ROTATE_LEFT('L'),
    ROTATE_RIGHT('R'),
    MOVE('M')

    private key

    ShipOperation(String key) {
        this.key = key
    }

    static String getSymbols() {
        collect { it.key }.join()
    }

    static ShipOperation fromSymbol(String symbol) {
        find { it.key == symbol }
    }

    String toString() {
        StringUtils.camelize(this.name())
    }
}