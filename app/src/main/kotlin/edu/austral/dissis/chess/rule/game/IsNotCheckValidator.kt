package edu.austral.dissis.chess.rule.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.piece.enum.PieceType
import edu.austral.dissis.chess.result.rule.*
import edu.austral.dissis.chess.rule.Rule

class IsNotCheckValidator: Rule {
    private val message = "That move leaves you in check"
    override fun validateMove(move: Move, board: Board): RuleResult {
        val boardAfterMove = board.movePiece(move.getFrom(), move.getTo())
        val kingPosition = getKingCoords(boardAfterMove, move.getTurn())
        val kingsTeam: Team = boardAfterMove.getPiece(kingPosition)?.team ?: throw NoSuchElementException("No king found")

        boardAfterMove.getOccupiedPositions().forEach { coordinate ->
            if (boardAfterMove.getPiece(coordinate)?.team != kingsTeam) {
                val piece = boardAfterMove.getPiece(coordinate) ?: throw NoSuchElementException("No piece found")
                when (
                    piece.validateMove(
                        Move(boardAfterMove, coordinate, kingPosition, piece, kingsTeam, emptyList() // TODO: Do I need to get the history of the piece to check for checks?
                        )
                    )
                ) {
                    ValidResult -> return InvalidResult(message)
                    is CheckMateResult -> return InvalidResult(message)
                    is ValidWithExecutionResult -> return InvalidResult(message)
                    is InvalidResult -> {} // continue
                }
            }
        }
        return ValidResult
    }

    private fun getKingCoords(board: Board, team: Team): Coordinate {
        board.getOccupiedPositions().forEach { coordinate ->
            if (board.getPiece(coordinate)?.name == PieceType.KING && board.getPiece(coordinate)?.team == team) {
                return coordinate
            }
        }
        throw NoSuchElementException("No king in board found")
    }
}
