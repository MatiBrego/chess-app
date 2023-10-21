package edu.austral.dissis.common.result.rule

class InvalidResult(
    private val message: String = "Cannot execute that move"
): RuleResult {
    fun getMessage(): String {
        return message
    }
}
