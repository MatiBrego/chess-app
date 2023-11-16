package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.rule.special.HasEnemyPieceInBetweenKingValidator
import edu.austral.dissis.checkers.rule.special.HasEnemyPieceInBetweenValidator
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.action.ConvertPiece
import edu.austral.dissis.common.result.action.RelativePosition
import edu.austral.dissis.common.rule.Rule
import edu.austral.dissis.common.rule.compound.And
import edu.austral.dissis.common.rule.compound.Or
import edu.austral.dissis.common.rule.direction.LeftValidator
import edu.austral.dissis.common.rule.direction.RightValidator
import edu.austral.dissis.common.rule.direction.VerticalBackwardsValidator
import edu.austral.dissis.common.rule.direction.VerticalForwardValidator
import edu.austral.dissis.common.rule.movequantity.ExactMovementValidator
import edu.austral.dissis.common.rule.movequantity.LimitedMovementValidator
import edu.austral.dissis.common.rule.orientation.DiagonalValidator
import edu.austral.dissis.common.rule.special.IsOpposingRowValidator


fun singleDiagonalForward(): Rule {
    return And(
        listOf(
            DiagonalValidator(),
            LimitedMovementValidator(1),
            VerticalForwardValidator(),
        )
    )
}

fun captureForward(): Rule{
    return And(
        listOf(
            VerticalForwardValidator(),
            DiagonalValidator(),
            ExactMovementValidator(2),
            HasEnemyPieceInBetweenValidator()
        )
    )
}

fun captureBackward(): Rule{
    return And(
        listOf(
            VerticalBackwardsValidator(),
            DiagonalValidator(),
            ExactMovementValidator(2),
            HasEnemyPieceInBetweenKingValidator()
        )
    )
}

fun singleDiagonal(): Rule {
    return And(
        listOf(
            DiagonalValidator(),
            LimitedMovementValidator(1),
        )
    )
}

fun crown(team: Team): Rule{
    return Or(
        listOf(
            normalCrown(team),
            captureCrown(team)
        )
    )
}

fun normalCrown(team: Team): Rule {
    return Or(
        listOf(
            normalCrownRight(team),
            normalCrownLeft(team)
        )
    )
}

fun normalCrownRight(team: Team): Rule {
    return And(
        listOf(
            singleDiagonalForward(),
            IsOpposingRowValidator(),
            RightValidator()
        )
    ).withActions(
        listOf(
            ConvertPiece(
                RelativePosition(1, 1),
                createKing(team)
            )
        )
    )
}

fun normalCrownLeft(team: Team): Rule {
    return And(
listOf(
            singleDiagonalForward(),
            IsOpposingRowValidator(),
            LeftValidator()
        )
    ).withActions(
        listOf(
            ConvertPiece(
                RelativePosition(1, -1),
                createKing(team)
            )
        )
    )
}

fun captureCrown(team: Team): Rule {
    return Or(
        listOf(
            captureCrownRight(team),
            captureCrownLeft(team)
        )
    )
}

fun captureCrownRight(team: Team): Rule {
    return And(
        listOf(
            captureForward(),
            IsOpposingRowValidator(),
            RightValidator()
        )
    ).withActions(
        listOf(
            ConvertPiece(
                RelativePosition(2, 2),
                createKing(team)
            )
        )
    )
}

fun captureCrownLeft(team: Team): Rule {
    return And(
        listOf(
            captureForward(),
            IsOpposingRowValidator(),
            RightValidator()
        )
    ).withActions(
        listOf(
            ConvertPiece(
                RelativePosition(2, -2),
                createKing(team)
            )
        )
    )
}