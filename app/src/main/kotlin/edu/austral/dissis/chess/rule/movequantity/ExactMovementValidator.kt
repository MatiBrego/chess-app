package edu.austral.dissis.chess.rule.movequantity

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

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
