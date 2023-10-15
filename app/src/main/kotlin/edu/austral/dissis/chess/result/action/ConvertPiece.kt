package edu.austral.dissis.chess.result.action

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.piece.Piece

class ConvertPiece(
    private val from: Coordinate,
    private val newPiece: Piece
    ): Action {
    fun getFrom(): Coordinate {
        return from
    }

    fun getNewPiece(): Piece {
        return newPiece
    }
}
