package edu.austral.dissis.chess.rule.game

import edu.austral.dissis.chess.piece.ChessPieceType
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.ValidResult

class CheckValidator {
    fun isCheck(board: Board, kingsTeam: Team): Boolean {
        val kingPosition = getKingCoords(board, kingsTeam)?: throw NoSuchElementException("No king found")
        val enemyPieceCoordinates = board.getOccupiedPositions()

        for(coordinate in enemyPieceCoordinates) {
            if (pieceAttacksKing(board, coordinate, kingsTeam, kingPosition)) {
                return true
            }
        }
        return false
    }

    private fun pieceAttacksKing(
        board: Board,
        coordinate: Coordinate,
        kingsTeam: Team,
        kingPosition: Coordinate
    ): Boolean {
        if (board.getPiece(coordinate)?.team != kingsTeam) {
            val piece = board.getPiece(coordinate) ?: throw NoSuchElementException("No piece found")
            when (
                piece.validateMove(
                    Move(coordinate, kingPosition, kingsTeam), board
                )
            ) {
                is ValidResult -> return true
                is InvalidResult -> {} //Continue
            }
        }
        return false
    }

    private fun getKingCoords(board: Board, team: Team): Coordinate? {
        board.getOccupiedPositions().forEach { coordinate ->
            if (board.getPiece(coordinate)?.pieceType == ChessPieceType.KING && board.getPiece(coordinate)?.team == team) {
                return coordinate
            }
        }
        return null
    }
}
