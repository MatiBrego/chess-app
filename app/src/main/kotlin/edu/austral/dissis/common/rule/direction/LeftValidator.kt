package edu.austral.dissis.common.rule.direction

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class LeftValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if(move.getFrom().column > move.getTo().column)
            ValidResult()
        else
            InvalidResult()
    }
}
