package com.intenthq.battleship.domain

enum Orientation {

    NORTH,
    EAST,
    SOUTH,
    WEST

    private static final PREV = -1
    private static final NEXT = +1

    static String getSymbols() {
        collect { firstLetter(it) }.join()
    }

    static Orientation fromSymbol(String symbol) {
        find { firstLetter(it) == symbol }
    }

    String toSymbol() {
        this.name().getAt(0)
    }

    Orientation rotateLeft() {
        Orientation.enumConstants[get(PREV)]
    }

    Orientation rotateRight() {
        Orientation.enumConstants[get(NEXT)]
    }

    boolean isHorizontal() {
        this in [EAST, WEST]
    }

    String toString() {
        this.name().toLowerCase().capitalize()
    }

    private static firstLetter(orientation) {
        orientation.toString().getAt(0)
    }

    private Integer get(value) {
        (values().size() + this.ordinal() + value) % values().size()
    }
}