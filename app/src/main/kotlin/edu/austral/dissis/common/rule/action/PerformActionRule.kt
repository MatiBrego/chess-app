package edu.austral.dissis.common.rule.action

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.action.Action
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidWithExecutionResult
import edu.austral.dissis.common.rule.Rule


class PerformActionRule(
    private val actions: List<Action>
): Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return ValidWithExecutionResult(
            actions
        )
    }
}
