package edu.austral.dissis.chess.game.checkmate

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.rule.Rule
import edu.austral.dissis.chess.rule.game.CheckValidator

class NormalValidator(
): CheckMateValidator {
    private val checkValidator = CheckValidator()

    override fun isCheckmate(board: Board, enemyTeam: Team, gameRules: List<Rule>): Boolean {
        val pieceCoordinates: List<Coordinate> = board.getOccupiedPositions().filter { coordinate ->
            board.getPiece(coordinate)?.team == enemyTeam }
        for (pieceCoordinate in pieceCoordinates) {
            val validMoves = findAllValidMoves(pieceCoordinate, board, gameRules)
            for (validMove in validMoves) {
                val boardAfterMove = board.movePiece(validMove.getFrom(), validMove.getTo())
                if (!checkValidator.isCheck(boardAfterMove, enemyTeam)) {
                    return false
                }
            }
        }

        return true
    }

    private fun findAllValidMoves(pieceCoordinate: Coordinate, board: Board, gameRules: List<Rule>): List<Move>{
        val piece = board.getPiece(pieceCoordinate) ?: throw NoSuchElementException("No piece found")
        val team = piece.team
        var validMoves = emptyList<Move>()
        for(i in 1..board.getRowQuantity()){
            for(j in 1..board.getColumnQuantity()){
                val to = Coordinate(i,j)
                val move = Move(board, pieceCoordinate, to, piece, team)
                if(piece.validateMove(move) is ValidResult && gameRules.all { it.validateMove(move, board) is ValidResult }){
                    validMoves = validMoves.plus(move)
                }
            }
        }
        return validMoves
    }
}
