package edu.austral.dissis.chess.rule.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class PieceIsPresentValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if (board.getPiece(move.getFrom()) == null) InvalidResult("No piece in that coordinate")
        else ValidResult
    }
}
