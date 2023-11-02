package edu.austral.dissis.common.rule

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.action.Action
import edu.austral.dissis.common.result.rule.RuleResult

interface Rule {
    fun validateMove(move: Move, board: Board): RuleResult
}
