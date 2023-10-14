package util.startingposition.bishop

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.factory.createBishop
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.startingposition.StartingPositionGenerator

class BishopInCenterBlocked: StartingPositionGenerator {
    override fun generate(): Map<Coordinate, Piece> {
        return mapOf(
            Coordinate(3, 3) to createBishop(Team.BLACK),
            Coordinate(2, 2) to createBishop(Team.WHITE),
            Coordinate(4, 2) to createBishop(Team.WHITE),
            Coordinate(2, 4) to createBishop(Team.WHITE),
            Coordinate(4, 4) to createBishop(Team.WHITE)
        )
    }
}
