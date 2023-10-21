package edu.austral.dissis.common.rule.movequantity

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class ExactMovementValidator(
    private val quantity: Int
): Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        val horizontalDistance = kotlin.math.abs(move.getFrom().column - move.getTo().column)
        val verticalDistance = kotlin.math.abs(move.getFrom().row - move.getTo().row)

        return if (horizontalDistance == quantity || verticalDistance == quantity) {
            ValidResult
        } else {
            InvalidResult()
        }
    }
}
