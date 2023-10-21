package edu.austral.dissis.common.result.rule

import edu.austral.dissis.common.result.action.Action

class ValidWithExecutionResult(
    private val actions: List<Action>
): RuleResult {
    fun getActions(): List<Action> {
        return actions
    }
}
