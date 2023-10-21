package edu.austral.dissis.common.result.action

import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Team

class ApplyMove(
    private val from: RelativePosition = RelativePosition(),
    private val to: RelativePosition = RelativePosition()
): Action {
    fun getFrom(reference: Coordinate): Coordinate {
        return from.getAbsolutePosition(reference)
    }

    fun getTo(reference: Coordinate): Coordinate {
        return to.getAbsolutePosition(reference)
    }
}
