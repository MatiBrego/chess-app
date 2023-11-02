package edu.austral.dissis.common.rule.special

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class HasEnemyValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        board.getPiece(move.getTo())?.let {
            if (it.team != move.getTurn()) return ValidResult()
            else return InvalidResult()
        }
        return InvalidResult()
    }

}
