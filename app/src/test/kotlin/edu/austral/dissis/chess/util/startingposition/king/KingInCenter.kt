package util.startingposition.king

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.factory.createKing
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.startingposition.StartingPositionGenerator

class KingInCenter: StartingPositionGenerator {
    override fun generate(): Map<Coordinate, Piece> {
        return mapOf(
            Coordinate(3, 3) to createKing(Team.WHITE)
        )
    }
}
