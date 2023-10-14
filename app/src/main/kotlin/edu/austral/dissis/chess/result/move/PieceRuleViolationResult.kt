package edu.austral.dissis.chess.result.move

class PieceRuleViolationResult(
    private val message: String
): MoveResult {
    fun getMessage(): String {
        return message
    }
}
