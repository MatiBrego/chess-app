package edu.austral.dissis.server.listener

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.BoardSize
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.server.GameServer
import edu.austral.dissis.server.payload.InitPayload
import edu.austral.dissis.server.payload.PiecePayload
import edu.austral.ingsis.clientserver.ServerConnectionListener

class InitListener(private val gameServer: GameServer): ServerConnectionListener{
    override fun handleClientConnection(clientId: String) {
        val currentGameState = gameServer.getGame();
        val payload = getPayload(currentGameState)
        gameServer.sendInit(clientId, payload)
        println("Client $clientId connected")
    }

    private fun getPayload(currentGameState: Game) = InitPayload(
        getBoardSize(currentGameState.getBoard()),
        getPieces(currentGameState.getBoard()),
        currentGameState.getTurn()
    )

    private fun getBoardSize(board: Board): BoardSize {
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

    override fun handleClientConnectionClosed(clientId: String) {
        println("Client $clientId disconnected")
    }
}