package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team

fun ceroIndexedNormalBoard(): Board {
    val map: MutableMap<Coordinate, Piece> = mutableMapOf()

    // Pawn
    for (i in 0..7) {
        map[Coordinate(1, i)] = createPawn(Team.WHITE)
        map[Coordinate(6, i)] = createPawn(Team.BLACK)
    }

    // Rook
    map[Coordinate(0, 0)] = createRook(Team.WHITE)
    map[Coordinate(0, 7)] = createRook(Team.WHITE)
    map[Coordinate(7, 0)] = createRook(Team.BLACK)
    map[Coordinate(7, 7)] = createRook(Team.BLACK)

    // Knight
    map[Coordinate(0, 1)] = createKnight(Team.WHITE)
    map[Coordinate(0, 6)] = createKnight(Team.WHITE)
    map[Coordinate(7, 1)] = createKnight(Team.BLACK)
    map[Coordinate(7, 6)] = createKnight(Team.BLACK)

    // Bishop
    map[Coordinate(0, 2)] = createBishop(Team.WHITE)
    map[Coordinate(0, 5)] = createBishop(Team.WHITE)
    map[Coordinate(7, 2)] = createBishop(Team.BLACK)
    map[Coordinate(7, 5)] = createBishop(Team.BLACK)

    // Queen
    map[Coordinate(0, 3)] = createQueen(Team.WHITE)
    map[Coordinate(7, 3)] = createQueen(Team.BLACK)

    // King
    map[Coordinate(0, 4)] = createKing(Team.WHITE)
    map[Coordinate(7, 4)] = createKing(Team.BLACK)

    return Board(map)
}

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
