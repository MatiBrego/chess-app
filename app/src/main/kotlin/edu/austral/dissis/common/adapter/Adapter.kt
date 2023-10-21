package edu.austral.dissis.common.adapter

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.MoveResult
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.move.EndOfGameResult
import edu.austral.dissis.common.result.move.SuccessfulResult
import edu.austral.dissis.common.result.move.UnsuccessfulResult

class Adapter(var game: Game): GameEngine{
    override fun applyMove(move: Move): MoveResult {
        val from = Coordinate(move.from.row, move.from.column)
        val to = Coordinate(move.to.row, move.to.column)

        return when(val result = game.move(from, to)){
            is SuccessfulResult -> {getNewGameState(result)}
            is UnsuccessfulResult -> {InvalidMove(result.getMessage())}
            is EndOfGameResult -> {GameOver(getPlayerColor(result.winner))}
        }
    }

    override fun init(): InitialState {
        return InitialState(
                getBoardSize(),
                getPieces(),
                getCurrentPlayerColor()
        )
    }

    private fun getBoardSize(): BoardSize {
        val board = game.getBoard()
        return BoardSize(board.getColumnQuantity(), board.getRowQuantity())
    }

    private fun getPieces(): List<ChessPiece>{
        val board = game.getBoard()
        return toChessPieces(board.getOccupiedPositions(), board)
    }

    private fun toChessPieces(coordinates: List<Coordinate>, board: Board): List<ChessPiece> {
        return coordinates.map {
            val piece = board.getPiece(it)!!
            ChessPiece(
                getId(piece),
                getPlayerColor(piece),
                getPiecePosition(it),
                getPieceType(piece)
            )
        }
    }

    private fun getPlayerColor(piece: Piece) =
        if (piece.team == Team.WHITE) PlayerColor.WHITE else PlayerColor.BLACK

    private fun getPlayerColor(team: Team) =
        if (team == Team.WHITE) PlayerColor.WHITE else PlayerColor.BLACK

    private fun getId(piece: Piece) = piece.getId()

    private fun getPiecePosition(coord: Coordinate) = Position(coord.row, coord.column)

    private fun getPieceType(piece: Piece) = piece.pieceType.value()

    private fun getCurrentPlayerColor(): PlayerColor {
        if(game.getTurn() == Team.WHITE) return PlayerColor.WHITE
        return PlayerColor.BLACK
    }

    private fun getNewGameState(result: SuccessfulResult): MoveResult {
        game = result.game
        return NewGameState(getPieces(), getCurrentPlayerColor())
    }
}
