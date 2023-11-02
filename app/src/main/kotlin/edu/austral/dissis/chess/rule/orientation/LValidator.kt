package edu.austral.dissis.chess.rule.orientation

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class LValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if  (
            (move.getFrom().row == move.getTo().row + 1 && move.getFrom().column == move.getTo().column + 2) ||
            (move.getFrom().row == move.getTo().row + 2 && move.getFrom().column == move.getTo().column + 1) ||
            (move.getFrom().row == move.getTo().row + 1 && move.getFrom().column == move.getTo().column - 2) ||
            (move.getFrom().row == move.getTo().row + 2 && move.getFrom().column == move.getTo().column - 1) ||
            (move.getFrom().row == move.getTo().row - 1 && move.getFrom().column == move.getTo().column + 2) ||
            (move.getFrom().row == move.getTo().row - 2 && move.getFrom().column == move.getTo().column + 1) ||
            (move.getFrom().row == move.getTo().row - 1 && move.getFrom().column == move.getTo().column - 2) ||
            (move.getFrom().row == move.getTo().row - 2 && move.getFrom().column == move.getTo().column - 1)
        ) {
            ValidResult()
        }else{
            InvalidResult()
        }
    }
}
