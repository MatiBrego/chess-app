package edu.austral.dissis.chess.piece

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.rule.Rule

data class Piece (
    val pieceType: PieceType,
    val pieceRule: Rule,
    val team: Team,
    private val id: String = "",
    private val moveCount: Int = 0){
    fun validateMove(move: Move, board: Board): RuleResult {
        return this.pieceRule.validateMove(move, board) // TODO: Should piece create the Move object?
    }

    override fun toString(): String {
        return "$pieceType (${team.toString()[0]})"
    }

    fun getId(): String {
        return if(id != "") id
        else this.hashCode().toString()
    }

    fun getMoveCount(): Int {
        return moveCount
    }
}

