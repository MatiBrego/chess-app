package edu.austral.dissis.client

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.gui.GameView
import edu.austral.dissis.client.listener.*
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.server.payload.*
import edu.austral.ingsis.clientserver.Client
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import java.net.InetSocketAddress

class GameClient{
    private lateinit var client: Client

    fun start(root: GameView) {
        client = buildClient(root)

        client.connect()

        client.send(Message("request-init", Unit))

        root.addListener(
            GameViewListener(this)
        )
    }

    fun applyMove(from: Coordinate, to: Coordinate){
        client.send(Message("move", MovePayload(from, to)))
    }

    private fun buildClient(root: GameView): Client{
        return NettyClientBuilder
            .createDefault()
            .withAddress(InetSocketAddress(8080))
            .addMessageListener(
                "init",
                object : TypeReference<Message<InitPayload>>() {},
                InitListener(root)
            )
            .addMessageListener(
                "new-game-state",
                object : TypeReference<Message<SuccessfulMovePayload>>() {},
                SuccessfulMoveListener(root)
            )
            .addMessageListener(
                "unsuccessful-move",
                object : TypeReference<Message<UnsuccessfulMovePayload>>() {},
                UnsuccessfulMoveListener(root)
            )
            .addMessageListener(
                "end-of-game",
                object : TypeReference<Message<EndOfGamePayload>>() {},
                EndOfGameListener(root)
            )
            .build()
    }
}

