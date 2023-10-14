package edu.austral.dissis.chess.result.move

class GameRuleViolationResult(
    private val message: String
) : MoveResult {
    fun getMessage(): String {
        return message
    }
}
