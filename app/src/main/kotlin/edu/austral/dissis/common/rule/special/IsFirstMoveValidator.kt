package edu.austral.dissis.common.rule.special

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class IsFirstMoveValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        val fromPiece = board.getPiece(move.getFrom()) ?: return InvalidResult("No piece in that coordinate")

        if (fromPiece.getMoveCount() == 0) return ValidResult
        return InvalidResult()
    }
}
