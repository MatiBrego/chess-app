package edu.austral.dissis.common.turn

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult

class OneEach(private val current: Team): Turn {
    override fun getNextTurn(move: Move, board: Board): Turn {
        return when (current){
            Team.WHITE -> OneEach(Team.BLACK)
            Team.BLACK -> OneEach(Team.WHITE)
        }
    }

    override fun getCurrentTeam(): Team {
        return current
    }

    override fun validateTurnRules(move: Move, board: Board): RuleResult {
        return ValidResult()
    }
}