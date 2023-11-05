package edu.austral.dissis.server

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.factory.createNormalChessGame
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.BoardSize
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.result.move.EndOfGameResult
import edu.austral.dissis.common.result.move.MoveResult
import edu.austral.dissis.common.result.move.SuccessfulResult
import edu.austral.dissis.common.result.move.UnsuccessfulResult
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder

fun main() {
    GameServer(
        createNormalChessGame(),
        NettyServerBuilder.createDefault()
    )
}

class GameServer(
    private var game: Game,
    builder: ServerBuilder
) {
    private val server: Server =
        builder
            .withPort(8080)
            .addMessageListener(
                "request-init",
                object : TypeReference<Message<Unit>>() {},
                InitListener(this)
            )
            .addMessageListener(
                "move",
                object : TypeReference<Message<MovePayload>>() {},
                MoveListener(this)
            )
            .addMessageListener(
                "test",
                object : TypeReference<Message<Any>>() {},
                TestListener()
            )
            .build()
    init {
        server.start()
    }

    fun getGame(): Game{
        return game
    }

    fun broadcastInit(payload: InitPayload){
        server.broadcast(Message("init", payload))
    }

    fun broadcastNewState(payload: NewEventPayload){
        when (payload){
            is SuccessfulMovePayload -> server.broadcast(Message("new-game-state", payload))
            is EndOfGamePayload -> server.broadcast(Message("end-of-game", payload))
            is UnsuccessfulMovePayload -> server.broadcast(Message("unsuccessful-move", payload))
        }
    }

    fun applyMove(movePayload: MovePayload): MoveResult{
        val from = movePayload.from
        val to = movePayload.to
        when (val result = game.move(from, to)){
            is EndOfGameResult -> return result
            is SuccessfulResult -> {
                game = result.game
                return result
            }
            is UnsuccessfulResult -> return result
        }
    }
}

class InitListener(private val gameServer: GameServer): MessageListener<Unit>{
    override fun handleMessage(message: Message<Unit>) {
        val currentGameState = gameServer.getGame();
        val payload = getPayload(currentGameState)
        gameServer.broadcastInit(payload)
    }

    private fun getPayload(currentGameState: Game) = InitPayload(
        getBoardSize(currentGameState.getBoard()),
        getPieces(currentGameState.getBoard()),
        currentGameState.getTurn()
    )

    private fun getBoardSize(board: Board): BoardSize{
        return board.getBoardSize()
    }

    private fun getPieces(board: Board): List<PiecePayload>{
        val pieces = board.getOccupiedPositions()
        return pieces.map {
            val piece = board.getPiece(it)!!
            PiecePayload(
                piece.getId(),
                piece.team,
                it,
                piece.pieceType.value()
            )
        }
    }
}

class MoveListener(private val gameServer: GameServer): MessageListener<MovePayload>{
    override fun handleMessage(message: Message<MovePayload>) {
        when (val result = gameServer.applyMove(message.payload)){
            is EndOfGameResult -> gameServer.broadcastNewState(EndOfGamePayload(result.winner))
            is SuccessfulResult -> {
                gameServer.broadcastNewState(getSuccessPayload(result.game))
            }
            is UnsuccessfulResult -> gameServer.broadcastNewState(UnsuccessfulMovePayload(result.getMessage()))
        }

    }

    private fun getSuccessPayload(game: Game): SuccessfulMovePayload{
        return SuccessfulMovePayload(
            game.getTurn(),
            getPieces(game.getBoard())
        )
    }

    private fun getPieces(board: Board): List<PiecePayload>{
        val pieces = board.getOccupiedPositions()
        return pieces.map {
            val piece = board.getPiece(it)!!
            PiecePayload(
                piece.getId(),
                piece.team,
                it,
                piece.pieceType.value()
            )
        }
    }
}

class TestListener(): MessageListener<Any>{
    override fun handleMessage(message: Message<Any>) {
        print("Test received!")
    }

}