package com.intenthq.battleship.presenter

trait ShipPresenter {

    private static final SUNK = 'SUNK'

    String format() {
        def status = sunk ? SUNK : ''
        "(${position.x}, ${position.y}, ${position.orientation.toSymbol()}) ${status}"
    }
}
