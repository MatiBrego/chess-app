package edu.austral.dissis.common.result.action

import edu.austral.dissis.common.board.Coordinate

class RemovePiece(
    private val from: RelativePosition
): Action {
    fun getFrom(originalFromCoord: Coordinate): Coordinate {
        return Coordinate(
            originalFromCoord.row + from.getRow(),
            originalFromCoord.column + from.getColumn()
        )
    }
}
