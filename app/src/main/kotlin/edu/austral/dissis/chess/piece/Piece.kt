package edu.austral.dissis.chess.piece

import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.piece.enum.PieceType
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.rule.Rule

data class Piece (
    val name: PieceType,
    val pieceRule: Rule,
    val team: Team,
    private val id: String = "",
    private val moveCount: Int = 0){
    fun validateMove(move: Move): RuleResult {
        return this.pieceRule.validateMove(move, move.getBoard()) // TODO: Should piece create the Move object?
    }

    override fun toString(): String {
        return "$name (${team.toString()[0]})"
    }

    fun getId(): String {
        return if(id != "") id
        else this.hashCode().toString()
    }

    fun getMoveCount(): Int {
        return moveCount
    }
}

