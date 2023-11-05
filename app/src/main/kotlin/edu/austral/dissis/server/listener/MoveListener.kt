package edu.austral.dissis.server.listener

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.result.move.EndOfGameResult
import edu.austral.dissis.common.result.move.SuccessfulResult
import edu.austral.dissis.common.result.move.UnsuccessfulResult
import edu.austral.dissis.server.GameServer
import edu.austral.dissis.server.payload.*
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class MoveListener(private val gameServer: GameServer): MessageListener<MovePayload> {
    override fun handleMessage(message: Message<MovePayload>) {
        when (val result = gameServer.applyMove(message.payload)){
            is EndOfGameResult -> gameServer.broadcastNewState(EndOfGamePayload(result.winner))
            is SuccessfulResult -> {
                gameServer.broadcastNewState(getSuccessPayload(result.game))
            }
            is UnsuccessfulResult -> gameServer.broadcastNewState(UnsuccessfulMovePayload(result.getMessage()))
        }

    }

    private fun getSuccessPayload(game: Game): SuccessfulMovePayload {
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
