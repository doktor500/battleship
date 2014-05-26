package com.intenthq.battleship.presenter

import static com.intenthq.Characters.NEW_LINE

trait BoardPresenter {

    String format() {
        ships.collect { it.format() }.join(NEW_LINE)
    }
}
