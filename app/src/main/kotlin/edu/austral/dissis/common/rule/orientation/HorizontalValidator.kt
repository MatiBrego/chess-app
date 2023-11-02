package edu.austral.dissis.common.rule.orientation

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class HorizontalValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if (move.getFrom().row == move.getTo().row) ValidResult()
        else InvalidResult()
    }
}
