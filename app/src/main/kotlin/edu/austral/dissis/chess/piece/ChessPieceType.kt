package edu.austral.dissis.chess.piece

import edu.austral.dissis.common.piece.PieceType

enum class ChessPieceType(private val value: String): PieceType {
    KING("king"),
    QUEEN("queen"),
    BISHOP("bishop"),
    KNIGHT("knight"),
    ROOK("rook"),
    PAWN("pawn"),
    ARCHBISHOP("archbishop"),
    CHANCELLOR("chancellor");

    override fun value(): String {
        return value
    }
}