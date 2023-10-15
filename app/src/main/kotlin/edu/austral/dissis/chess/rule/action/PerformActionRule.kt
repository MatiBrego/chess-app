package edu.austral.dissis.chess.rule.action

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.NormalBoard
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.action.Action
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.result.rule.ValidWithExecutionResult
import edu.austral.dissis.chess.rule.Rule


class PerformActionRule(
    private val actions: List<Action>
): Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        return ValidWithExecutionResult(
            actions
        )
    }
}
