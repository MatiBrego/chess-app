package edu.austral.dissis.chess.rule.orientation

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class VerticalValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if (move.getFrom().column == move.getTo().column) ValidResult
        else InvalidResult()
    }
}
