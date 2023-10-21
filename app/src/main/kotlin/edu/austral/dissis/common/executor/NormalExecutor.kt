package edu.austral.dissis.common.executor

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.action.*

class NormalExecutor: MoveExecutor {

    override fun executeSingleMove(move: Move, board: Board): Board {
        return applyMove(move.getFrom(), move.getTo(), board)
    }

    override fun executeActions(actions: List<Action>, move: Move, board: Board): Board {
        val from: Coordinate = move.getFrom()
        var modifiedBoard: Board = board
        val isForwardPositive = isForwardPositive(move)

        for (action in actions){
            modifiedBoard = when (action){
                is ApplyMove ->
                    applyMove(
                        action.getFrom(from, isForwardPositive),
                        action.getTo(from, isForwardPositive),
                        modifiedBoard)
                is RemovePiece ->
                    removePiece(
                        action.getFrom(from, isForwardPositive),
                        modifiedBoard)
                is ConvertPiece ->
                    convertPiece(
                        action.getFrom(from, isForwardPositive),
                        modifiedBoard,
                        action.getNewPiece())
            }
        }
        return modifiedBoard
    }

    private fun applyMove(from: Coordinate, to: Coordinate, modifiedBoard: Board): Board {
        return modifiedBoard.movePiece(from, to)
    }

    private fun removePiece(coordinate: Coordinate, modifiedBoard: Board): Board {
        return modifiedBoard.removePiece(coordinate)
    }

    private fun convertPiece(coordinate: Coordinate, modifiedBoard: Board, newPiece: Piece): Board {
        val oldPiece: Piece = modifiedBoard.getPiece(coordinate) ?: throw NoSuchElementException("No piece found")
        return modifiedBoard.addPiece(coordinate, oldPiece.copy(
            pieceType = newPiece.pieceType,
            team = newPiece.team,
            pieceRule = newPiece.pieceRule,
            id = oldPiece.getId()
        )
        )
    }
    private fun isForwardPositive(move: Move): Boolean {
        return move.getTurn() == Team.WHITE
    }
}
