package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.piece.ChessPieceType
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.action.ApplyMove
import edu.austral.dissis.common.result.action.ConvertPiece
import edu.austral.dissis.common.result.action.RelativePosition
import edu.austral.dissis.common.rule.action.PerformActionRule
import edu.austral.dissis.common.rule.compound.And
import edu.austral.dissis.common.rule.compound.Or
import edu.austral.dissis.common.rule.direction.LeftValidator
import edu.austral.dissis.common.rule.direction.RightValidator
import edu.austral.dissis.common.rule.direction.VerticalForwardValidator
import edu.austral.dissis.common.rule.movequantity.ExactMovementValidator
import edu.austral.dissis.common.rule.movequantity.LimitedMovementValidator
import edu.austral.dissis.common.rule.obstacle.DiagonalObstacleValidator
import edu.austral.dissis.common.rule.obstacle.HorizontalObstacleValidator
import edu.austral.dissis.common.rule.obstacle.VerticalObstacleValidator
import edu.austral.dissis.common.rule.orientation.DiagonalValidator
import edu.austral.dissis.common.rule.orientation.HorizontalValidator
import edu.austral.dissis.chess.rule.orientation.LValidator
import edu.austral.dissis.common.rule.orientation.VerticalValidator
import edu.austral.dissis.common.rule.special.HasEnemyValidator
import edu.austral.dissis.common.rule.special.IsFirstMoveValidator
import edu.austral.dissis.common.rule.special.IsOpposingRowValidator

fun createRook(team: Team): Piece {
    return Piece(
        ChessPieceType.ROOK,
        Or(
            listOf(
                And(
                    listOf(
                        HorizontalValidator(),
                        HorizontalObstacleValidator()
                    )
                ),
                And(
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
        ChessPieceType.BISHOP,
        And(
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
        ChessPieceType.QUEEN,
        Or(
            listOf(
                And(
                    listOf(
                        HorizontalValidator(),
                        HorizontalObstacleValidator()
                    )
                ),
                And(
                    listOf(
                        VerticalValidator(),
                        VerticalObstacleValidator()
                    )
                ),
                And(
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
        ChessPieceType.KING,
        Or(
            listOf(
                And(
                    listOf(
                        Or(
                            listOf(
                                HorizontalValidator(),
                                VerticalValidator(),
                                DiagonalValidator(),
                            )
                        ),
                        LimitedMovementValidator(1)
                    )
                ),
                And(
                    listOf(
                        IsFirstMoveValidator(),
                        HorizontalValidator(),
                        HorizontalObstacleValidator(),
                        ExactMovementValidator(2),
                        LeftValidator(),
                        PerformActionRule(
                            listOf(
                                ApplyMove(RelativePosition(0, -4), RelativePosition(0, -1)),
                            )
                        )
                    )
                ),
                And(
                    listOf(
                        IsFirstMoveValidator(),
                        HorizontalValidator(),
                        HorizontalObstacleValidator(),
                        ExactMovementValidator(2),
                        RightValidator(),
                        PerformActionRule(
                            listOf(
                                ApplyMove(RelativePosition(0, 3), RelativePosition(0, 1)),
                            )
                        )
                    )
                )
            )
        ),
        team
    )
}

fun createKnight(team: Team): Piece {
    return Piece(
        ChessPieceType.KNIGHT,
        LValidator(),
        team
    )
}

fun createPawn(team: Team): Piece {
    return Piece(
        ChessPieceType.PAWN,
        Or(
            listOf(
                And(
                    listOf( // Crowning
                        VerticalValidator(),
                        LimitedMovementValidator(1),
                        VerticalObstacleValidator(true),
                        VerticalForwardValidator(),
                        IsOpposingRowValidator(),
                        PerformActionRule(
                            listOf(
                                ConvertPiece(RelativePosition(1, 0), createQueen(team))
                            )
                        )
                    )
                ),
                And( // When eating diagonally
                    listOf(
                        HasEnemyValidator(),
                        DiagonalValidator(),
                        LimitedMovementValidator(1),
                    )
                ),
                And( // When it is the first move
                    listOf(
                        IsFirstMoveValidator(),
                        VerticalValidator(),
                        LimitedMovementValidator(2),
                        VerticalObstacleValidator(true),
                        VerticalForwardValidator()
                    )
                ),
                And( // When it is not the first move
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

fun createArchbishop(team: Team): Piece {
    return Piece(
            ChessPieceType.ARCHBISHOP,
        Or(
            listOf(
                And(
                    listOf(
                        DiagonalValidator(),
                        DiagonalObstacleValidator()
                    )
                ),
                LValidator()
            )
        ),
            team
    )
}

fun createChancellor(team: Team): Piece {
    return Piece(
        ChessPieceType.CHANCELLOR,
        Or(
            listOf(
                And(
                    listOf(
                        HorizontalValidator(),
                        HorizontalObstacleValidator()
                    )
                ),
                And(
                    listOf(
                        VerticalValidator(),
                        VerticalObstacleValidator()
                    )
                ),
                LValidator()
            )
        ),
        team
    )
}
