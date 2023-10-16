package edu.austral.dissis.chess.rule.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class IsYourTurnValidator: Rule {
    private val message = "It's not your turn"
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if(move.getPiece().team == move.getTurn()) ValidResult
        else InvalidResult(message)
    }
}
