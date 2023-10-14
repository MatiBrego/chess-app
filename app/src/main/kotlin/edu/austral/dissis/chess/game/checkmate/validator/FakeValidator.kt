package edu.austral.dissis.chess.game.checkmate.validator

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class FakeValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return ValidResult
    }
}
