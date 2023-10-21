package edu.austral.dissis.common.rule.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class PieceIsPresentValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if (board.getPiece(move.getFrom()) == null) InvalidResult("No piece in that coordinate")
        else ValidResult
    }
}
