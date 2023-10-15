package edu.austral.dissis.chess.rule.special

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule

class IsOpposingRowValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if(move.getTurn() == Team.WHITE){
            if(move.getTo().row == board.getRowQuantity()){
                ValidResult
            }else
                InvalidResult()
        }else
            if(move.getTo().row == 1){
                ValidResult
            }else
                InvalidResult()
    }
}
