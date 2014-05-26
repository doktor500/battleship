package com.intenthq.battleship.presenter

trait ShipPresenter {

    String format() {
        def status = sunk ? SUNK : ''
        "(${position.x}, ${position.y}, ${position.orientation.toSymbol()}) ${status}"
    }
}
