package edu.austral.dissis.common.result.action

import edu.austral.dissis.common.board.Coordinate

// Represents the relative position of a piece in relation to the `from` coordinate in a move.
class RelativePosition(
    private val row: Int = 0,
    private val column: Int = 0,
) {
    fun getRow(): Int {
        return row
    }

    fun getColumn(): Int {
        return column
    }

    fun getAbsolutePosition(reference: Coordinate, isForwardPositive: Boolean): Coordinate{
        return Coordinate(
            reference.row + row * if (isForwardPositive) 1 else -1,
            reference.column + column
        )
    }
}
