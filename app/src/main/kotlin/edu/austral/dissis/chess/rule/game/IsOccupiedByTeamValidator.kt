package edu.austral.dissis.chess.rule.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class IsOccupiedByTeamValidator: Rule {
    private val message = "That square is occupied by a piece of your team"
    override fun validateMove(move: Move, board: Board): RuleResult {
        val fromPiece = board.getPiece(move.getFrom()) ?: return InvalidResult("No piece in that coordinate")

        board.getPiece(move.getTo())?.let {
            if(it.team == fromPiece.team) return InvalidResult(message)
            else return ValidResult
        }
        return ValidResult    }
}
