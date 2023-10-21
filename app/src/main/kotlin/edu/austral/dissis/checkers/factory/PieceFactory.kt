package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.piece.CheckersPieceType
import edu.austral.dissis.checkers.rule.special.HasEnemyInPositionValidator
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.action.RelativePosition
import edu.austral.dissis.common.result.action.RemovePiece
import edu.austral.dissis.common.rule.action.PerformActionRule
import edu.austral.dissis.common.rule.compound.And
import edu.austral.dissis.common.rule.compound.Or
import edu.austral.dissis.common.rule.movequantity.LimitedMovementValidator
import edu.austral.dissis.common.rule.orientation.DiagonalValidator

fun createMan(team: Team): Piece {
    return Piece(
        pieceType = CheckersPieceType.Man,
        Or(
            listOf(
                And(
                    listOf(
                        DiagonalValidator(),
                        LimitedMovementValidator(1)
                    )
                ),
                And(
                    listOf(
                        DiagonalValidator(),
                        LimitedMovementValidator(2),
                        HasEnemyInPositionValidator(
                            RelativePosition(1, 1)
                        ),
                        PerformActionRule(
                            listOf(
                                RemovePiece(
                                    RelativePosition(1, 1)
                                )
                            )
                        )
                    )
                ),
                And(
                    listOf(
                        DiagonalValidator(),
                        LimitedMovementValidator(2),
                        HasEnemyInPositionValidator(
                            RelativePosition(1, -1)
                        ),
                        PerformActionRule(
                            listOf(
                                RemovePiece(
                                    RelativePosition(1, -1)
                                )
                            )
                        )
                    )
                )
            )
        ),team
    )
}