package edu.austral.dissis.common.result.action

import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Piece

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
