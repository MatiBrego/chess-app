package edu.austral.dissis.client

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.server.*
import edu.austral.ingsis.clientserver.Client
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.stage.Stage
import java.net.InetSocketAddress


fun main() {
    Application.launch(GameClient::class.java)
}

class GameClient : Application(){
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Chess"
    }

    lateinit var client: Client

    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle

        val root = GameView(imageResolver)
        primaryStage.scene = Scene(root)

        client = NettyClientBuilder.createDefault().withAddress(
            InetSocketAddress(8080)
        ).addMessageListener(
            "init",
            object : TypeReference<Message<InitPayload>>() {},
            InitListener(root)
        ).addMessageListener(
            "new-game-state",
            object : TypeReference<Message<SuccessfulMovePayload>>() {},
            SuccessfulMoveListener(root)
        ).addMessageListener(
            "unsuccessful-move",
            object : TypeReference<Message<UnsuccessfulMovePayload>>() {},
            UnsuccessfulMoveListener(root)
        ).addMessageListener(
            "end-of-game",
            object : TypeReference<Message<EndOfGamePayload>>() {},
            EndOfGameListener(root)
        )
        .build()

        client.connect()

        client.send(Message("request-init", Unit))

        primaryStage.show()

        root.addListener(
            GameViewListener(this)
        )
    }

    fun applyMove(from: Coordinate, to: Coordinate){
        client.send(Message("move", MovePayload(from, to)))
    }
}

class InitListener(private val root: GameView): MessageListener<InitPayload> {
    override fun handleMessage(message: Message<InitPayload>) {
        val initState = getInitialState(message.payload)
        Platform.runLater {
            root.handleInitialState(initState)
        }
    }

    private fun getInitialState(payload: InitPayload): InitialState {
        return InitialState(
            getBoardSize(payload),
            getPieces(payload),
            getPlayerColor(payload.team)
        )
    }

    private fun getBoardSize(payload: InitPayload): BoardSize {
        return BoardSize(payload.boardSize.getColumns(), payload.boardSize.getRows())
    }

    private fun getPieces(payload: InitPayload): List<ChessPiece> {
        return toChessPieces(payload.pieces)
    }

    private fun toChessPieces(pieces: List<PiecePayload>): List<ChessPiece> {
        return pieces.map {
            ChessPiece(
                it.id,
                getPlayerColor(it.team),
                getPiecePosition(it.position),
                it.pieceId
            )
        }
    }

    private fun getPlayerColor(team: Team) =
        if (team == Team.WHITE) PlayerColor.WHITE else PlayerColor.BLACK
    private fun getPiecePosition(coord: Coordinate) = Position(coord.row, coord.column)
}

class SuccessfulMoveListener(private val root: GameView): MessageListener<SuccessfulMovePayload> {
    override fun handleMessage(message: Message<SuccessfulMovePayload>) {
        val adaptedMoveResult = adaptNewState(message.payload.turn, message.payload.piecePayload)
        Platform.runLater {
            root.handleMoveResult(adaptedMoveResult)
        }
    }

    private fun adaptNewState(team: Team, pieces: List<PiecePayload>): NewGameState {
        return NewGameState(
            toChessPieces(pieces),
            getPlayerColor(team)
        )
    }

    private fun toChessPieces(pieces: List<PiecePayload>): List<ChessPiece> {
        return pieces.map {
            ChessPiece(
                it.id,
                getPlayerColor(it.team),
                getPiecePosition(it.position),
                it.pieceId
            )
        }
    }

    private fun getPiecePosition(coord: Coordinate) = Position(coord.row, coord.column)

    private fun getPlayerColor(team: Team) =
        if (team == Team.WHITE) PlayerColor.WHITE else PlayerColor.BLACK
}

class UnsuccessfulMoveListener(private val root: GameView): MessageListener<UnsuccessfulMovePayload> {
    override fun handleMessage(message: Message<UnsuccessfulMovePayload>) {
        Platform.runLater {
            root.handleMoveResult(InvalidMove(message.payload.message))
        }
    }
}

class EndOfGameListener(private val root: GameView): MessageListener<EndOfGamePayload> {
    override fun handleMessage(message: Message<EndOfGamePayload>) {
        Platform.runLater {
            root.handleMoveResult(GameOver(getPlayerColor(message.payload.winner)))
        }
    }

    private fun getPlayerColor(team: Team) =
        if (team == Team.WHITE) PlayerColor.WHITE else PlayerColor.BLACK
}

class GameViewListener(private val client: GameClient):  GameEventListener{
    override fun handleMove(move: Move) {
        client.applyMove(
            Coordinate(move.from.row, move.from.column),
            Coordinate(move.to.row, move.to.column)
        )
    }

}