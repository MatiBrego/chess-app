package edu.austral.dissis.client.listener

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.server.payload.InitPayload
import edu.austral.dissis.server.payload.PiecePayload
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import javafx.application.Platform

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