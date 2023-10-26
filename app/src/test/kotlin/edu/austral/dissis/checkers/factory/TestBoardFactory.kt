package edu.austral.dissis.checkers.factory

import edu.austral.dissis.chess.factory.createBishop
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Team

fun manInCenter(): Board{
    return Board(mapOf(Coordinate(3, 3) to createMan(Team.WHITE)))
}

fun manWithOneCapture(): Board{
    return Board(
        mapOf(
            Coordinate(3, 3) to createMan(Team.WHITE),
            Coordinate(4, 4) to createMan(Team.BLACK)
        )
    )
}

fun manWith3Captures(): Board{
    return Board(
        mapOf(
            Coordinate(1, 1) to createMan(Team.WHITE),
            Coordinate(2, 2) to createMan(Team.BLACK),
            Coordinate(4, 4) to createMan(Team.BLACK),
            Coordinate(6, 4) to createMan(Team.BLACK)
        )
    )
}