package edu.austral.dissis.client.listener

import edu.austral.dissis.chess.gui.GameOver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.dissis.chess.gui.PlayerColor
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.server.payload.EndOfGamePayload
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import javafx.application.Platform

class EndOfGameListener(private val root: GameView): MessageListener<EndOfGamePayload> {
    override fun handleMessage(message: Message<EndOfGamePayload>) {
        Platform.runLater {
            root.handleMoveResult(GameOver(getPlayerColor(message.payload.winner)))
        }
    }

    private fun getPlayerColor(team: Team) =
        if (team == Team.WHITE) PlayerColor.WHITE else PlayerColor.BLACK
}