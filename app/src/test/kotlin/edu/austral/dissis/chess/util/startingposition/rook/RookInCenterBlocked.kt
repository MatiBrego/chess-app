package util.startingposition.rook

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.factory.createRook
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.startingposition.StartingPositionGenerator

class RookInCenterBlocked: StartingPositionGenerator {
    override fun generate(): Map<Coordinate, Piece> {
        return mapOf(
            Coordinate(3, 3) to createRook(Team.WHITE),
            Coordinate(2, 3) to createRook(Team.BLACK),
            Coordinate(4, 3) to createRook(Team.BLACK),
            Coordinate(3, 2) to createRook(Team.BLACK),
            Coordinate(3, 4) to createRook(Team.BLACK)
        )

    }
}
