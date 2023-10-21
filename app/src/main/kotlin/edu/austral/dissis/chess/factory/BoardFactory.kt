package edu.austral.dissis.chess.factory

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team

fun createNormalStartingBoard(): Board {
    val map: MutableMap<Coordinate, Piece> = mutableMapOf()

    // Pawn
    for (i in 1..8) {
        map[Coordinate(2, i)] = createPawn(Team.WHITE)
        map[Coordinate(7, i)] = createPawn(Team.BLACK)
    }

    // Rook
    map[Coordinate(1, 1)] = createRook(Team.WHITE)
    map[Coordinate(1, 8)] = createRook(Team.WHITE)
    map[Coordinate(8, 1)] = createRook(Team.BLACK)
    map[Coordinate(8, 8)] = createRook(Team.BLACK)

    // Knight
    map[Coordinate(1, 2)] = createKnight(Team.WHITE)
    map[Coordinate(1, 7)] = createKnight(Team.WHITE)
    map[Coordinate(8, 2)] = createKnight(Team.BLACK)
    map[Coordinate(8, 7)] = createKnight(Team.BLACK)

    // Bishop
    map[Coordinate(1, 3)] = createBishop(Team.WHITE)
    map[Coordinate(1, 6)] = createBishop(Team.WHITE)
    map[Coordinate(8, 3)] = createBishop(Team.BLACK)
    map[Coordinate(8, 6)] = createBishop(Team.BLACK)

    // Queen
    map[Coordinate(1, 4)] = createQueen(Team.WHITE)
    map[Coordinate(8, 4)] = createQueen(Team.BLACK)

    // King
    map[Coordinate(1, 5)] = createKing(Team.WHITE)
    map[Coordinate(8, 5)] = createKing(Team.BLACK)

    return Board(map)
}

fun castleSetupBoard(): Board {
    return Board(
        mapOf(
        Pair(Coordinate(1, 1), createRook(Team.WHITE)),
        Pair(Coordinate(1, 5), createKing(Team.WHITE)),
        Pair(Coordinate(1, 8), createRook(Team.WHITE)),
        Pair(Coordinate(8, 8), createRook(Team.BLACK)),
        Pair(Coordinate(8, 5), createKing(Team.BLACK)),
        Pair(Coordinate(8, 1), createRook(Team.BLACK))
        )
    )
}

fun crowningSetupBoard(): Board {
    return Board(
        mapOf(
            Pair(Coordinate(7, 1), createPawn(Team.WHITE)),
            Pair(Coordinate(1, 5), createKing(Team.WHITE)),
            Pair(Coordinate(2, 8), createPawn(Team.BLACK)),
            Pair(Coordinate(8, 5), createKing(Team.BLACK))
        )
    )
}

fun createBoardWithSpecialPieces(): Board {
    val map: MutableMap<Coordinate, Piece> = mutableMapOf()

    // Pawn
    for (i in 1..8) {
        map[Coordinate(2, i)] = createPawn(Team.WHITE)
        map[Coordinate(7, i)] = createPawn(Team.BLACK)
    }

    // Chancellor
    map[Coordinate(1, 1)] = createChancellor(Team.WHITE)
    map[Coordinate(1, 8)] = createChancellor(Team.WHITE)
    map[Coordinate(8, 1)] = createChancellor(Team.BLACK)
    map[Coordinate(8, 8)] = createChancellor(Team.BLACK)

    // Knight
    map[Coordinate(1, 2)] = createKnight(Team.WHITE)
    map[Coordinate(1, 7)] = createKnight(Team.WHITE)
    map[Coordinate(8, 2)] = createKnight(Team.BLACK)
    map[Coordinate(8, 7)] = createKnight(Team.BLACK)

    // Archbishop
    map[Coordinate(1, 3)] = createArchbishop(Team.WHITE)
    map[Coordinate(1, 6)] = createArchbishop(Team.WHITE)
    map[Coordinate(8, 3)] = createArchbishop(Team.BLACK)
    map[Coordinate(8, 6)] = createArchbishop(Team.BLACK)

    // Queen
    map[Coordinate(1, 4)] = createQueen(Team.WHITE)
    map[Coordinate(8, 4)] = createQueen(Team.BLACK)

    // King
    map[Coordinate(1, 5)] = createKing(Team.WHITE)
    map[Coordinate(8, 5)] = createKing(Team.BLACK)

    return Board(map)
}
