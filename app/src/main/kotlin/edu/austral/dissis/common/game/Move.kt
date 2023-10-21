package edu.austral.dissis.common.game

import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Team

data class Move(
    private val from: Coordinate,
    private val to: Coordinate,
    private val turn: Team,
) {
    fun getFrom(): Coordinate {
        return from
    }

    fun getTo(): Coordinate {
        return to
    }

    fun getTurn(): Team {
        return turn
    }
}
