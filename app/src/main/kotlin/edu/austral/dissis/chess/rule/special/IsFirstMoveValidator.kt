package edu.austral.dissis.chess.rule.special

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class IsFirstMoveValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        if (move.getHistory().isEmpty()) return ValidResult
        return InvalidResult()
    }
}
