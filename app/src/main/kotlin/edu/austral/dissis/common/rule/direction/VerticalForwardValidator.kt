package edu.austral.dissis.common.rule.direction

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

// Remember what is considered forward depends on the team
class VerticalForwardValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if (move.getTurn() == Team.WHITE) {
            validateWhiteDirection(move.getFrom(), move.getTo())
        } else {
            validateBlackDirection(move.getFrom(), move.getTo())
        }
    }

    private fun validateWhiteDirection(from: Coordinate, to: Coordinate): RuleResult {
        return if (from.row < to.row){
            ValidResult()
        }else{
            InvalidResult()
        }
    }

    private fun validateBlackDirection(from: Coordinate, to: Coordinate): RuleResult {
        return if (from.row > to.row){
            ValidResult()
        }else {
            InvalidResult()
        }
    }

}
