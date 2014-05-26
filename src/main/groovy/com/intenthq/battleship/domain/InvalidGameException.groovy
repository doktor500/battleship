package com.intenthq.battleship.domain

class InvalidGameException extends RuntimeException {

    InvalidGameException(String msg) {
        super(msg)
    }
}