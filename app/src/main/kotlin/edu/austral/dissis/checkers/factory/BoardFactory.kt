package edu.austral.dissis.checkers.factory

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team

fun createCheckersStartingBoard(): Board {
    val map: MutableMap<Coordinate, Piece> = mutableMapOf()

//     For the WHITE team, we will use rows 1-3, and for the BLACK team, we will use rows 6-8.
//     Only the pieces on the dark squares are considered, which alternate depending on the row.

    // WHITE checkers
    for (i in 1..3) {
        val startColumn = if (i % 2 == 1) 2 else 1 // Start from 2nd column for odd rows and 1st column for even rows
        for (j in startColumn..8 step 2) {
            map[Coordinate(i, j)] = createMan(Team.WHITE)
        }
    }

    // BLACK checkers
    for (i in 6..8) {
        val startColumn = if (i % 2 == 1) 2 else 1 // Start from 2nd column for odd rows and 1st column for even rows
        for (j in startColumn..8 step 2) {
            map[Coordinate(i, j)] = createMan(Team.BLACK)
        }
    }
    return Board(map)
}
