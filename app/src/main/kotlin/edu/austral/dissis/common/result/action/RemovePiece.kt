package edu.austral.dissis.common.result.action

import edu.austral.dissis.common.board.Coordinate

class RemovePiece(
    private val from: RelativePosition
): Action {
    fun getFrom(reference: Coordinate, isForwardPositive: Boolean): Coordinate {
        return from.getAbsolutePosition(reference, isForwardPositive)
    }
}
