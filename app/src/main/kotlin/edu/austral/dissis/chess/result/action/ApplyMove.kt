package edu.austral.dissis.chess.result.action

import edu.austral.dissis.chess.board.Coordinate

class ApplyMove(
    private val from: Coordinate,
    private val to: Coordinate
): Action {
    fun getFrom(): Coordinate {
        return from
    }

    fun getTo(): Coordinate {
        return to
    }
}
