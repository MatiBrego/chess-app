package edu.austral.dissis.client.listener

import edu.austral.dissis.chess.gui.GameView
import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.dissis.server.payload.UnsuccessfulMovePayload
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import javafx.application.Platform

class UnsuccessfulMoveListener(private val root: GameView): MessageListener<UnsuccessfulMovePayload> {
    override fun handleMessage(message: Message<UnsuccessfulMovePayload>) {
        Platform.runLater {
            root.handleMoveResult(InvalidMove(message.payload.message))
        }
    }
}