package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.piece.CheckersPieceType
import edu.austral.dissis.checkers.rule.special.CheckersCaptureValidator
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.rule.compound.And
import edu.austral.dissis.common.rule.compound.Or
import edu.austral.dissis.common.rule.direction.VerticalForwardValidator
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
                        LimitedMovementValidator(1),
                        VerticalForwardValidator(),
                    )
                ),
//                And(
//                    listOf(
//                        DiagonalValidator(),
//                        ExactMovementValidator(2),
//                        HasEnemyInPositionValidator(
//                            RelativePosition(1, 1)
//                        ),
//                        VerticalForwardValidator(),
//                        PerformActionRule(
//                            listOf(
//                                RemovePiece(
//                                    RelativePosition(1, 1)
//                                )
//                            )
//                        )
//                    )
//                ),
//                And(
//                    listOf(
//                        DiagonalValidator(),
//                        ExactMovementValidator(2),
//                        HasEnemyInPositionValidator(
//                            RelativePosition(1, -1)
//                        ),
//                        VerticalForwardValidator(),
//                        PerformActionRule(
//                            listOf(
//                                RemovePiece(
//                                    RelativePosition(1, -1)
//                                )
//                            )
//                        )
//                    )
//                ),
                And(
                    listOf(
                        VerticalForwardValidator(),
                        CheckersCaptureValidator()
                    )
                )

            )
        ),team
    )
}