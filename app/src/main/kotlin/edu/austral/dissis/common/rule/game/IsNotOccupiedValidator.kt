package edu.austral.dissis.common.rule.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.rule.Rule
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.ValidResult

class IsNotOccupiedValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if (board.getPiece(move.getTo()) == null) {
            ValidResult()
        } else {
            InvalidResult("The position is occupied")
        }
    }
}