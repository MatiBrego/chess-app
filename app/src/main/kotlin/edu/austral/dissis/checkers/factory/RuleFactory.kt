package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.rule.special.CheckersCaptureValidator
import edu.austral.dissis.common.rule.Rule
import edu.austral.dissis.common.rule.compound.And
import edu.austral.dissis.common.rule.direction.VerticalForwardValidator
import edu.austral.dissis.common.rule.movequantity.LimitedMovementValidator
import edu.austral.dissis.common.rule.orientation.DiagonalValidator

fun singleDiagonalForward(): Rule {
    return And(
        listOf(
            DiagonalValidator(),
            LimitedMovementValidator(1),
            VerticalForwardValidator(),
        )
    )
}

fun captureForward(): Rule {
    return And(
        listOf(
            VerticalForwardValidator(),
            CheckersCaptureValidator()
        )
    )
}