package edu.austral.dissis.chess.adapter

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.game.Game
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.MoveResult
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.piece.enum.PieceType
import edu.austral.dissis.chess.result.move.*

class Adapter(var game: Game): GameEngine{
    override fun applyMove(move: Move): MoveResult {
        val from = Coordinate(move.from.row, move.from.column)
        val to = Coordinate(move.to.row, move.to.column)
        return when(val result = game.move(from, to)){
            is SuccessfulResult -> {getNewGameState(result)}
            is GameRuleViolationResult -> {InvalidMove(result.getMessage())}
            is PieceRuleViolationResult -> {InvalidMove(result.getMessage())}
            is EndOfGameResult -> {GameOver(getPlayerColor(result.winner))}
            is NoPieceInCoordinateResult -> {InvalidMove("No piece in coordinate")}
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
        return board.getOccupiedPositions().map {
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

    private fun getPieceType(piece: Piece) = when(piece.name){
        PieceType.BISHOP -> "bishop"
        PieceType.KING -> "king"
        PieceType.KNIGHT -> "knight"
        PieceType.PAWN -> "pawn"
        PieceType.QUEEN -> "queen"
        PieceType.ROOK -> "rook"
    }

    private fun getCurrentPlayerColor(): PlayerColor {
        if(game.getTurn() == Team.WHITE) return PlayerColor.WHITE
        return PlayerColor.BLACK
    }

    private fun getNewGameState(result: SuccessfulResult): MoveResult {
        game = result.game
        return NewGameState(getPieces(), getCurrentPlayerColor())
    }
}
