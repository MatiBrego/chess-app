package edu.austral.dissis.common.turn

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.rule.RuleResult

interface Turn {
    fun getNextTurn(move: Move, board: Board): Turn

    fun getCurrentTeam(): Team

    fun validateTurnRules(move: Move, board: Board): RuleResult
}