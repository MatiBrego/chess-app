package edu.austral.dissis.common.rule.orientation

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule
import kotlin.math.abs

class DiagonalValidator: Rule {

    override fun validateMove(move: Move, board: Board): RuleResult {
        return if (abs(move.getFrom().row - move.getTo().row) == abs(move.getFrom().column - move.getTo().column)) {
            ValidResult()
        }else{
            InvalidResult()
        }

    }
}
