package edu.austral.dissis.chess.startingposition

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.factory.createKing
import edu.austral.dissis.chess.factory.createRook
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team

class CastleStart: StartingPositionGenerator {
    override fun generate(): Map<Coordinate, Piece> {
        return mapOf(
            Pair(Coordinate(1, 1), createRook(Team.WHITE)),
            Pair(Coordinate(1, 5), createKing(Team.WHITE)),
            Pair(Coordinate(8, 8), createRook(Team.BLACK)),
            Pair(Coordinate(8, 5), createKing(Team.BLACK))
        )
    }
}
