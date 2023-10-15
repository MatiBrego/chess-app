package edu.austral.dissis.chess.result.action

import edu.austral.dissis.chess.board.Coordinate

class RemovePiece(
    private val from: Coordinate
): Action {
    fun getFrom(): Coordinate {
        return from
    }
}
