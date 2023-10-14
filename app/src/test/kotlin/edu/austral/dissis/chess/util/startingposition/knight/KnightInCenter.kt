package util.startingposition.knight

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.factory.createKnight
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.startingposition.StartingPositionGenerator

class KnightInCenter: StartingPositionGenerator {
    override fun generate(): Map<Coordinate, Piece> {
        return mapOf(
            Coordinate(3, 3) to createKnight(Team.WHITE)
        )
    }
}
