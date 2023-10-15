package edu.austral.dissis.chess.result.action

// Represents the relative position of a piece in relation to the `from` coordinate in a move.
class RelativePosition(
    private val row: Int = 0,
    private val column: Int = 0
) {
    fun getRow(): Int {
        return row
    }

    fun getColumn(): Int {
        return column
    }
}
