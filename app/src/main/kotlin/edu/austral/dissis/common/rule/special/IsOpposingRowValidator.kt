package edu.austral.dissis.common.rule.special

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class IsOpposingRowValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return if(move.getTurn() == Team.WHITE){
            if(move.getTo().row == board.getRowQuantity()){
                ValidResult()
            }else
                InvalidResult()
        }else
            if(move.getTo().row == 1){
                ValidResult()
            }else
                InvalidResult()
    }
}
