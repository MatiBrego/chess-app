package edu.austral.dissis.chess.piece

import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.piece.enum.PieceType
import edu.austral.dissis.chess.result.rule.RuleResult
import edu.austral.dissis.chess.rule.Rule

data class Piece (val name: PieceType, val pieceRule: Rule, val team: Team){
    fun validateMove(move: Move): RuleResult {
        return this.pieceRule.validateMove(move, move.getBoard()) // TODO: Should piece create the Move object?
    }

    override fun toString(): String {
        return "$name (${team.toString()[0]})"
    }

    fun getId(): String {
        return this.hashCode().toString()
    }
}

