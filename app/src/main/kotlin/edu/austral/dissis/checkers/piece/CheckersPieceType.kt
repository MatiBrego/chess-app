package edu.austral.dissis.checkers.piece

import edu.austral.dissis.common.piece.PieceType

enum class CheckersPieceType(private val value: String): PieceType {
    Man("pawn");

    override fun value(): String {
        return value
    }
}