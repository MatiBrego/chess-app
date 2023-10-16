package edu.austral.dissis.chess.rule.orientation

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

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
            ValidResult
        }else{
            InvalidResult()
        }
    }
}
