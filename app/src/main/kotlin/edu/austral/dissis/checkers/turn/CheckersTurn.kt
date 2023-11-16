package edu.austral.dissis.checkers.turn

import edu.austral.dissis.checkers.factory.captureBackward
import edu.austral.dissis.checkers.factory.captureForward
import edu.austral.dissis.checkers.piece.CheckersPieceType
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.BoardSize
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.turn.Turn

class CheckersTurn(
    private val current: Team,
    private val hasToCapture: Boolean = false,
    private val prevPieceId: String = "",
): Turn {

    private val manCaptureValidator = captureForward()
    private val kingCaptureValidator = captureBackward()

    override fun getNextTurn(move: Move, board: Board): Turn {
        return if (nextHasToBeCapture(move, board))
            createTurnWithCapture(move, board)
        else
            creatTurnWithoutCapture()
    }

    override fun getCurrentTeam(): Team {
        return current
    }

    override fun validateTurnRules(move: Move, board: Board): RuleResult {
        val piece = board.getPiece(move.getFrom()) ?: return InvalidResult("Piece not found")
        return if (hasToCapture)
            if (piece.pieceType == CheckersPieceType.King && isKingCapture(move, board) && isTheSamePiece(move, board))
                ValidResult()
            else if (isManCapture(move, board) && isTheSamePiece(move, board))
                ValidResult()
            else
                InvalidResult("Move must be a capture with the same piece")
        else
            ValidResult()
    }

    private fun nextHasToBeCapture(move: Move, board: Board): Boolean{
        return if (board.getPiece(move.getFrom())?.pieceType == CheckersPieceType.Man)
            (isManCapture(move, board) || hasToCapture) && manHasAvailableCaptures(move, board)
        else
            (isKingCapture(move, board, ) || hasToCapture) && kingHasAvailableCaptures(move, board)
    }

    private fun manHasAvailableCaptures(move: Move, board: Board): Boolean{
        val boardSize = board.getBoardSize()
        val forward = if(current == Team.WHITE) 1 else -1

        val left = Move(
            move.getTo(),
            Coordinate(move.getTo().row + (2 * forward), move.getTo().column - 2),
            current
        )
        if (isInBounds(left, boardSize) && isManCapture(left, board))
            return true

        val right = Move(
            move.getTo(),
            Coordinate(move.getTo().row + (2 * forward), move.getTo().column + 2),
            current
        )

        return isInBounds(right, boardSize) && isManCapture(right, board)
    }

    private fun kingHasAvailableCaptures(move: Move, board: Board): Boolean{
        val boardSize = board.getBoardSize()

        val leftForward = Move(
            move.getTo(),
            Coordinate(move.getTo().row - 2, move.getTo().column - 2),
            current
        )
        if (isInBounds(leftForward, boardSize) && isKingCapture(leftForward, board))
            return true

        val rightForward = Move(
            move.getTo(),
            Coordinate(move.getTo().row - 2, move.getTo().column + 2),
            current
        )
        if (isInBounds(rightForward, boardSize) && isKingCapture(rightForward, board))
            return true

        val leftBackward = Move(
            move.getTo(),
            Coordinate(move.getTo().row + 2, move.getTo().column - 2),
            current
        )
        if (isInBounds(leftBackward, boardSize) && isKingCapture(leftBackward, board))
            return true

        val rightBackward = Move(
            move.getTo(),
            Coordinate(move.getTo().row + 2, move.getTo().column + 2),
            current
        )

        return isInBounds(rightBackward, boardSize) && isKingCapture(rightBackward, board)
    }

    private fun isInBounds(move: Move, board: BoardSize): Boolean{
        return  move.getTo().row > 0 &&
                move.getTo().row <= board.getRows() &&
                move.getTo().column > 0 &&
                move.getTo().column <= board.getColumns()
    }

    private fun isManCapture(move: Move, board: Board): Boolean {
        if (board.getPiece(move.getTo()) != null)
            return false
        val ruleResult = manCaptureValidator.validateMove(move, board)

        return when (ruleResult) {
            is ValidResult -> true
            is InvalidResult -> false
        }
    }

    private fun isKingCapture(move: Move, board: Board): Boolean{
        if (board.getPiece(move.getTo()) != null)
            return false
        val ruleResult = kingCaptureValidator.validateMove(move, board)

        return when (ruleResult) {
            is ValidResult -> true
            is InvalidResult -> false
        }
    }

    private fun isTheSamePiece(move: Move, board: Board): Boolean {
        return board.getPiece(move.getFrom())?.getId() == prevPieceId
    }

    private fun createTurnWithCapture(move: Move, board: Board): Turn {
        val pieceId = board.getPiece(move.getFrom())!!.getId()
        return CheckersTurn(
            current,
            true,
            pieceId
        )
    }

    private fun creatTurnWithoutCapture(): Turn {
        return CheckersTurn(
            getOpposingTeam(),
            false
        )
    }

    private fun getOpposingTeam(): Team{
        return when (current){
            Team.WHITE -> Team.BLACK
            Team.BLACK -> Team.WHITE
        }
    }
}