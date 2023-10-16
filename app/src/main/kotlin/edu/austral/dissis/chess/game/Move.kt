package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team

data class Move(
    private val from: Coordinate,
    private val to: Coordinate,
    private val piece: Piece,
    private val turn: Team,
) {
    fun getFrom(): Coordinate {
        return from
    }

    fun getTo(): Coordinate {
        return to
    }

    fun getPiece(): Piece {
        return piece
    }

    fun getTurn(): Team {
        return turn
    }
}
