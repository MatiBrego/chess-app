package edu.austral.dissis.common.rule.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class IsOccupiedByTeamValidator: Rule {
    private val message = "That square is occupied by a piece of your team"
    override fun validateMove(move: Move, board: Board): RuleResult {
        val fromPiece = board.getPiece(move.getFrom()) ?: return InvalidResult("No piece in that coordinate")

        board.getPiece(move.getTo())?.let {
            if(it.team == fromPiece.team) return InvalidResult(message)
            else return ValidResult()
        }
        return ValidResult()
    }
}
