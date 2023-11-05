package edu.austral.dissis.client.listener

import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.client.GameClient
import edu.austral.dissis.common.board.Coordinate

class GameViewListener(private val client: GameClient): GameEventListener {
    override fun handleMove(move: Move) {
        client.applyMove(
            Coordinate(move.from.row, move.from.column),
            Coordinate(move.to.row, move.to.column)
        )
    }

}