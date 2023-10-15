package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.piece.enum.PieceType
import edu.austral.dissis.chess.result.action.ApplyMove
import edu.austral.dissis.chess.result.action.ConvertPiece
import edu.austral.dissis.chess.result.action.RelativePosition
import edu.austral.dissis.chess.rule.action.PerformActionRule
import edu.austral.dissis.chess.rule.direction.VerticalForwardValidator
import edu.austral.dissis.chess.rule.movequantity.LimitedMovementValidator
import edu.austral.dissis.chess.rule.obstacle.DiagonalObstacleValidator
import edu.austral.dissis.chess.rule.obstacle.HorizontalObstacleValidator
import edu.austral.dissis.chess.rule.obstacle.VerticalObstacleValidator
import edu.austral.dissis.chess.rule.orientation.DiagonalValidator
import edu.austral.dissis.chess.rule.orientation.HorizontalValidator
import edu.austral.dissis.chess.rule.orientation.LValidator
import edu.austral.dissis.chess.rule.orientation.VerticalValidator
import edu.austral.dissis.chess.rule.special.HasEnemyValidator
import edu.austral.dissis.chess.rule.special.IsFirstMoveValidator
import edu.austral.dissis.chess.rule.compound.AndRule
import edu.austral.dissis.chess.rule.compound.OrRule
import edu.austral.dissis.chess.rule.special.IsOpposingRowValidator

fun createRook(team: Team): Piece {
    return Piece(
        PieceType.ROOK,
        OrRule(
            listOf(
                AndRule(
                    listOf(
                        HorizontalValidator(),
                        HorizontalObstacleValidator()
                    )
                ),
                AndRule(
                    listOf(
                        VerticalValidator(),
                        VerticalObstacleValidator()
                    )
                )
            )
        ),
        team
    )
}

fun createBishop(team: Team): Piece {
    return Piece(
        PieceType.BISHOP,
        AndRule(
            listOf(
                DiagonalValidator(),
                DiagonalObstacleValidator()
            )
        ),
        team
    )
}

fun createQueen(team: Team): Piece {
    return Piece(
        PieceType.QUEEN,
        OrRule(
            listOf(
                AndRule(
                    listOf(
                        HorizontalValidator(),
                        HorizontalObstacleValidator()
                    )
                ),
                AndRule(
                    listOf(
                        VerticalValidator(),
                        VerticalObstacleValidator()
                    )
                ),
                AndRule(
                    listOf(
                        DiagonalValidator(),
                        DiagonalObstacleValidator()
                    )
                )
            )
        ),
        team
    )
}



fun createKing(team: Team): Piece {
    return Piece(
        PieceType.KING,
        AndRule(
            listOf(
                OrRule(
                    listOf(
                        HorizontalValidator(),
                        VerticalValidator(),
                        DiagonalValidator(),
                    )
                ),
                LimitedMovementValidator(1)
            )
        ),
        team
    )
}

fun createKnight(team: Team): Piece {
    return Piece(
        PieceType.KNIGHT,
        LValidator(),
        team
    )
}

fun createPawn(team: Team): Piece {
    return Piece(
        PieceType.PAWN,
        OrRule(
            listOf(
                AndRule(
                    listOf( // Crowning
                        VerticalValidator(),
                        LimitedMovementValidator(1),
                        VerticalObstacleValidator(true),
                        VerticalForwardValidator(),
                        IsOpposingRowValidator(),
                        PerformActionRule(
                            listOf(
                                ApplyMove(RelativePosition(0, 0), RelativePosition(1, 0)),
                                ConvertPiece(RelativePosition(1, 0), createQueen(team))
                            )
                        )
                    )
                ),
                AndRule( // When eating diagonally
                    listOf(
                        HasEnemyValidator(),
                        DiagonalValidator(),
                        LimitedMovementValidator(1),
                    )
                ),
                AndRule( // When it is the first move
                    listOf(
                        IsFirstMoveValidator(),
                        VerticalValidator(),
                        LimitedMovementValidator(2),
                        VerticalObstacleValidator(true),
                        VerticalForwardValidator()
                    )
                ),
                AndRule( // When it is not the first move
                    listOf(
                        VerticalValidator(),
                        LimitedMovementValidator(1),
                        VerticalObstacleValidator(true),
                        VerticalForwardValidator()
                    )
                ),
            )
        ),
        team
    )


}
