package edu.austral.dissis.common.result.rule

import edu.austral.dissis.common.result.action.Action

class ValidResult(
    private val actions: List<Action> = emptyList()
): RuleResult {
    fun getActions(): List<Action> {
        return actions
    }
}