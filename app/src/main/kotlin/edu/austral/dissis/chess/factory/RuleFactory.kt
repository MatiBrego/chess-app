package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.rule.orientation.LValidator
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.action.ApplyMove
import edu.austral.dissis.common.result.action.ConvertPiece
import edu.austral.dissis.common.result.action.RelativePosition
import edu.austral.dissis.common.rule.Rule
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
import edu.austral.dissis.common.rule.orientation.VerticalValidator
import edu.austral.dissis.common.rule.special.HasEnemyValidator
import edu.austral.dissis.common.rule.special.IsFirstMoveValidator
import edu.austral.dissis.common.rule.special.IsOpposingRowValidator

fun moveHorizontally(): Rule{
    return And(
        listOf(
            HorizontalValidator(),
            HorizontalObstacleValidator()
        )
    )
}

fun moveVertically(): Rule{
    return And(
        listOf(
            VerticalValidator(),
            VerticalObstacleValidator()
        )
    )
}

fun moveDiagonally(): Rule{
    return And(
        listOf(
            DiagonalValidator(),
            DiagonalObstacleValidator()
        )
    )
}

fun moveL(): Rule{
    return LValidator()
}

fun move1(): Rule{
    return And(
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
    )
}

fun castleRight(): Rule{
    return And(
        listOf(
            IsFirstMoveValidator(),
            HorizontalValidator(),
            HorizontalObstacleValidator(),
            ExactMovementValidator(2),
            RightValidator(),
        )
    ).withActions(
        listOf(
            ApplyMove(RelativePosition(0, 3), RelativePosition(0, 1)),
        )
    )
}

fun castleLeft(): Rule{
    return And(
        listOf(
            IsFirstMoveValidator(),
            HorizontalValidator(),
            HorizontalObstacleValidator(),
            ExactMovementValidator(2),
            LeftValidator(),
        )
    ).withActions(
        listOf(
            ApplyMove(RelativePosition(0, -4), RelativePosition(0, -1)),
        )
    )
}

fun pawnMoveForward(): Rule{
    return And(
        listOf(
            VerticalValidator(),
            LimitedMovementValidator(1),
            VerticalObstacleValidator(true),
            VerticalForwardValidator()
        )
    )
}

fun pawnMoveForwardFirstMove(): Rule{
    return And(
        listOf(
            IsFirstMoveValidator(),
            VerticalValidator(),
            LimitedMovementValidator(2),
            VerticalObstacleValidator(true),
            VerticalForwardValidator()
        )
    )
}

fun pawnCapture(): Rule{
    return And(
        listOf(
            HasEnemyValidator(),
            DiagonalValidator(),
            LimitedMovementValidator(1),
        )
    )
}

fun crown(team: Team): Rule{
    return And(
        listOf( // Crowning
            VerticalValidator(),
            LimitedMovementValidator(1),
            VerticalObstacleValidator(true),
            VerticalForwardValidator(),
            IsOpposingRowValidator(),
        )
    ).withActions(
        listOf(
            ConvertPiece(RelativePosition(1, 0), createQueen(team))
        )
    )
}