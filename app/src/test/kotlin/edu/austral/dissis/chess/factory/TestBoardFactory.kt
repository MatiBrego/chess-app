package edu.austral.dissis.chess.factory

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team

fun bishopInCenter(): Board {
    return Board(mapOf(Coordinate(3, 3) to createBishop(Team.WHITE)))
}
fun bishopInCenterBlocked(): Board {
    return Board(
        mapOf(
            Coordinate(3, 3) to createBishop(Team.BLACK),
            Coordinate(2, 2) to createBishop(Team.WHITE),
            Coordinate(4, 2) to createBishop(Team.WHITE),
            Coordinate(2, 4) to createBishop(Team.WHITE),
            Coordinate(4, 4) to createBishop(Team.WHITE)
        )
    )
}

fun kingInCenter(): Board {
    return Board(
        mapOf(
            Coordinate(3, 3) to createKing(Team.WHITE)
        )
    )
}

fun kingInCenterWithRookAndEnemyRook(): Board {
    return Board(mapOf(
        Coordinate(3, 3) to createKing(Team.WHITE),
        Coordinate(4, 3) to createRook(Team.WHITE),
        Coordinate(5, 3) to createRook(Team.BLACK)
    ))
}

fun knightInCenter(): Board {
    return Board(mapOf(
        Coordinate(3, 3) to createKnight(Team.WHITE)
    ))
}
fun pawnInCenter(): Board {
    return Board(mapOf(
        Coordinate(3, 3) to createPawn(Team.WHITE)
    ))
}
fun pawnInCenterBlocked(): Board {
    return Board(mapOf(
        Coordinate(3, 3) to createPawn(Team.WHITE),
        Coordinate(4, 3) to createPawn(Team.BLACK),
        Coordinate(4, 2) to createPawn(Team.BLACK),
        Coordinate(4, 4) to createPawn(Team.BLACK)
    ))
}

fun queenInCenter(): Board {
    return Board(mapOf(Coordinate(3, 3) to createQueen(Team.WHITE)))
}

fun rookInCenter(): Board {
    return Board(mapOf(Coordinate(3, 3) to createRook(Team.WHITE)))
}


fun rookInCenterBlocked(): Board {
    return Board(mapOf(
        Coordinate(3, 3) to createRook(Team.WHITE),
        Coordinate(2, 3) to createRook(Team.BLACK),
        Coordinate(4, 3) to createRook(Team.BLACK),
        Coordinate(3, 2) to createRook(Team.BLACK),
        Coordinate(3, 4) to createRook(Team.BLACK)
    ))

}

fun rookInCenterBlockedByTeam(): Board {
    return Board(mapOf(
        Coordinate(3, 3) to createRook(Team.WHITE),
        Coordinate(2, 3) to createRook(Team.WHITE),
        Coordinate(4, 3) to createRook(Team.WHITE),
        Coordinate(3, 2) to createRook(Team.WHITE),
        Coordinate(3, 4) to createRook(Team.WHITE)
    ))

    }
