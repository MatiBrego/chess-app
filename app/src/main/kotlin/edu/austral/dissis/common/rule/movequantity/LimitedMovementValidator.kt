package edu.austral.dissis.common.rule.movequantity

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class LimitedMovementValidator(
    private val maxMoveQuantity: Int
): Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        // Orthogonal Check
        val horizontalDistance = kotlin.math.abs(move.getFrom().column - move.getTo().column)
        val verticalDistance = kotlin.math.abs(move.getFrom().row - move.getTo().row)

        return if (horizontalDistance <= maxMoveQuantity && verticalDistance <= maxMoveQuantity) {
            ValidResult()
        } else {
            InvalidResult()
        }
    }

}
