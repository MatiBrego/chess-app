package edu.austral.dissis.chess.rule.direction

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.NormalBoard
import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

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
            ValidResult
        }else{
            InvalidResult()
        }
    }

    private fun validateBlackDirection(from: Coordinate, to: Coordinate): RuleResult {
        return if (from.row > to.row){
            ValidResult
        }else {
            InvalidResult()
        }
    }

}
