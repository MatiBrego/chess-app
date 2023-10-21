package edu.austral.dissis.common.result.action

import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team
import java.lang.module.ModuleReference

class ConvertPiece(
    private val from: RelativePosition,
    private val newPiece: Piece
    ): Action {
    fun getFrom(reference: Coordinate, isForwardPositive: Boolean): Coordinate {
        return from.getAbsolutePosition(reference, isForwardPositive)
    }

    fun getNewPiece(): Piece {
        return newPiece
    }
}
