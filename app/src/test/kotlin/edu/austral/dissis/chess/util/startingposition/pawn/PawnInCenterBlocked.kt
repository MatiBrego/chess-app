package util.startingposition.pawn

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.factory.createPawn
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.startingposition.StartingPositionGenerator

class PawnInCenterBlocked: StartingPositionGenerator {
    override fun generate(): Map<Coordinate, Piece> {
        return mapOf(
            Coordinate(3, 3) to createPawn(Team.WHITE),
            Coordinate(4, 3) to createPawn(Team.BLACK),
            Coordinate(4, 2) to createPawn(Team.BLACK),
            Coordinate(4, 4) to createPawn(Team.BLACK)
        )
    }
}
