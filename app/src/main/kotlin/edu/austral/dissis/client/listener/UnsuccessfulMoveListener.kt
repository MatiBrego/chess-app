package edu.austral.dissis.client.listener

import edu.austral.dissis.chess.gui.GameView
import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.dissis.server.payload.UnsuccessfulMovePayload
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class UnsuccessfulMoveListener(private val root: GameView): MessageListener<UnsuccessfulMovePayload> {
    override fun handleMessage(message: Message<UnsuccessfulMovePayload>) {
        root.handleMoveResult(InvalidMove(message.payload.message))
    }
}