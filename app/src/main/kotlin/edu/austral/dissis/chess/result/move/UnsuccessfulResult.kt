package edu.austral.dissis.chess.result.move

class UnsuccessfulResult(private val message: String): MoveResult {
    fun getMessage(): String {
        return message
    }
}
