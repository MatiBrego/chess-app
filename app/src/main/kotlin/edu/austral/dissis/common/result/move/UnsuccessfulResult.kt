package edu.austral.dissis.common.result.move

class UnsuccessfulResult(private val message: String): MoveResult {
    fun getMessage(): String {
        return message
    }
}
