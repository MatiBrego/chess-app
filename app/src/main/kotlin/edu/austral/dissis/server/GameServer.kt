package edu.austral.dissis.server

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.result.move.EndOfGameResult
import edu.austral.dissis.common.result.move.MoveResult
import edu.austral.dissis.common.result.move.SuccessfulResult
import edu.austral.dissis.common.result.move.UnsuccessfulResult
import edu.austral.dissis.server.listener.InitListener
import edu.austral.dissis.server.listener.MoveListener
import edu.austral.dissis.server.payload.*
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder

class GameServer(
    private var game: Game,
    private val builder: ServerBuilder = NettyServerBuilder.createDefault()
) {
    private val server: Server
    init {
        server = buildServer()
        server.start()
    }

    fun getGame(): Game{
        return game
    }

    fun sendInit(clientId: String, payload: InitPayload){
        server.sendMessage(clientId, Message("init", payload))
    }

    fun broadcastNewState(payload: NewEventPayload){
        when (payload){
            is SuccessfulMovePayload -> server.broadcast(Message("new-game-state", payload))
            is EndOfGamePayload -> server.broadcast(Message("end-of-game", payload))
            is UnsuccessfulMovePayload -> server.broadcast(Message("unsuccessful-move", payload))
        }
    }

    fun applyMove(movePayload: MovePayload): MoveResult{
        print("Applying move $movePayload \n")
        val from = movePayload.from
        val to = movePayload.to
        return when (val result = game.move(from, to)){
            is EndOfGameResult -> result
            is SuccessfulResult -> {
                game = result.game
                result
            }
            is UnsuccessfulResult -> result
        }
    }

    private fun buildServer(): Server{
        return builder
            .withPort(8080)
            .withConnectionListener(
                InitListener(this)
            )
            .addMessageListener(
                "move",
                object : TypeReference<Message<MovePayload>>() {},
                MoveListener(this)
            )
            .build()
    }
}