package edu.austral.dissis.chess.rule.special

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class IsFirstMoveValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        val fromPiece = board.getPiece(move.getFrom()) ?: return InvalidResult("No piece in that coordinate")

        if (fromPiece.getMoveCount() == 0) return ValidResult
        return InvalidResult()
    }
}
