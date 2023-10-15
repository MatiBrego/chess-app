package edu.austral.dissis.chess.rule.orientation

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.NormalBoard
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class HorizontalValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if (move.getFrom().row == move.getTo().row) ValidResult
        else InvalidResult()
    }
}
