package util.startingposition.queen

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.factory.createQueen
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.startingposition.StartingPositionGenerator

class QueenInCenter: StartingPositionGenerator {

    override fun generate(): Map<Coordinate, Piece> {
        return mapOf(Coordinate(3, 3) to createQueen(Team.WHITE))
    }
}
