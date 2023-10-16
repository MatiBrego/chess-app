package edu.austral.dissis.chess.rule.special

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class HasEnemyValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        board.getPiece(move.getTo())?.let {
            if (it.team != move.getTurn()) return ValidResult
            else return InvalidResult()
        }
        return InvalidResult()
    }

}
