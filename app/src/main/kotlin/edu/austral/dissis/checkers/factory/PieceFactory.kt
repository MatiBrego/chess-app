package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.piece.CheckersPieceType
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.rule.compound.And
import edu.austral.dissis.common.rule.compound.Or

fun createMan(team: Team): Piece {
    return Piece(
        pieceType = CheckersPieceType.Man,
        Or(
            listOf(
                singleDiagonalForward(),
                captureForward()
            )
        ),team
    )
}