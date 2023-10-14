package edu.austral.dissis.chess.rule.movequantity

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class LimitedMovementValidator(
    private val maxMoveQuantity: Int
): Rule {
    // TODO: Check if this is right
    override fun validateMove(move: Move, board: Board): RuleResult {
        // Orthogonal Check
        val horizontalDistance = kotlin.math.abs(move.getFrom().column - move.getTo().column)
        val verticalDistance = kotlin.math.abs(move.getFrom().row - move.getTo().row)

        return if (horizontalDistance <= maxMoveQuantity && verticalDistance <= maxMoveQuantity) {
            ValidResult
        } else {
            InvalidResult()
        }
    }

}
