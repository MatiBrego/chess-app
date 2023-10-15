package edu.austral.dissis.chess.result.rule

import edu.austral.dissis.chess.result.action.Action

class ValidWithExecutionResult(
    private val actions: List<Action>
): RuleResult {
    fun getActions(): List<Action> {
        return actions
    }
}
