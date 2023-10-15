package edu.austral.dissis.chess.result.action

import edu.austral.dissis.chess.board.Coordinate

class ApplyMove(
    private val from: RelativePosition = RelativePosition(),
    private val to: RelativePosition = RelativePosition()
): Action {
    fun getFrom(originalCoord: Coordinate): Coordinate {
        return Coordinate(
            originalCoord.row + from.getRow(),
            originalCoord.column + from.getColumn()
        )
    }

    fun getTo(originalCoord: Coordinate): Coordinate {
        return Coordinate(
            originalCoord.row + to.getRow(),
            originalCoord.column + to.getColumn()
        )
    }
}
