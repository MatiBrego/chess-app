package edu.austral.dissis.common.result.action

import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Team

class ApplyMove(
    private val from: RelativePosition = RelativePosition(),
    private val to: RelativePosition = RelativePosition()
): Action {
    fun getFrom(reference: Coordinate, isForwardPositive: Boolean): Coordinate {
        return from.getAbsolutePosition(reference, isForwardPositive)
    }

    fun getTo(reference: Coordinate, isForwardPositive: Boolean): Coordinate {
        return to.getAbsolutePosition(reference, isForwardPositive)
    }
}
