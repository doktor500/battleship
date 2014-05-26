package com.intenthq.battleship

class InvalidGameDataException extends RuntimeException {

    InvalidGameDataException(String msg) {
        super(msg)
    }
}