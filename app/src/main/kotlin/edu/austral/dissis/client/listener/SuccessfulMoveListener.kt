package edu.austral.dissis.client.listener

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.server.payload.PiecePayload
import edu.austral.dissis.server.payload.SuccessfulMovePayload
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class SuccessfulMoveListener(private val root: GameView): MessageListener<SuccessfulMovePayload> {
    override fun handleMessage(message: Message<SuccessfulMovePayload>) {
        val adaptedMoveResult = adaptNewState(message.payload.turn, message.payload.piecePayload)
        root.handleMoveResult(adaptedMoveResult)
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