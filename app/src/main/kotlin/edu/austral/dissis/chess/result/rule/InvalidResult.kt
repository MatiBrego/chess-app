package edu.austral.dissis.chess.result.rule

class InvalidResult(
    private val message: String = "Cannot execute that move"
): RuleResult {
    fun getMessage(): String {
        return message
    }
}
