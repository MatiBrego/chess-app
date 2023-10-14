package edu.austral.dissis.chess.startingposition

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.piece.Piece

interface StartingPositionGenerator {
    fun generate(): Map<Coordinate, Piece>
}
