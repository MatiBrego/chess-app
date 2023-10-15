package edu.austral.dissis.chess.result.action

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.piece.Piece

class ConvertPiece(
    private val from: RelativePosition,
    private val newPiece: Piece
    ): Action {
    fun getFrom(originalFromCoord: Coordinate): Coordinate {
        return Coordinate(
            originalFromCoord.row + from.getRow(),
            originalFromCoord.column + from.getColumn()
        )
    }

    fun getNewPiece(): Piece {
        return newPiece
    }
}
