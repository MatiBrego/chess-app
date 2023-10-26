package edu.austral.dissis.checkers.rule.special

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.action.RelativePosition
import edu.austral.dissis.common.result.action.RemovePiece
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidWithExecutionResult
import edu.austral.dissis.common.rule.Rule
import kotlin.math.abs

class CheckersCaptureValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        val captures = isValidCaptureMove(move.getFrom(), move.getTo(), board, move.getTurn())

        return if(captures.isNotEmpty() && !hasCapturesAvailable(move.getTo(), board, move.getTurn())) {
            getValidWithExecutionResult(captures, move.getFrom())
        } else {
            InvalidResult()
        }
    }

    private fun isValidCaptureMove(from: Coordinate, to: Coordinate, board: Board, team: Team): List<Coordinate> {
        var captures = isSingleCapture(from, to, board, team)
        if(captures.isNotEmpty()) {
            return captures
        }else {
            val potentialCaptures = getPotentialCaptures(from, board, team)
            for (potentialCapture in potentialCaptures) {
                captures = isValidCaptureMove(potentialCapture.first, to, board, team)
                if(captures.isNotEmpty()) {
                    return captures + potentialCapture.second
                }
            }
        }
        return emptyList()
    }

    private fun isSingleCapture(from: Coordinate, to: Coordinate, board: Board, team: Team): List<Coordinate> {
        // Ensure the move is diagonal and spans 2 squares
        if (abs(from.row - to.row) == 2 && abs(from.column - to.column) == 2) {
            // Calculate the coordinates of the intermediate square
            val midRow = (from.row + to.row) / 2
            val midColumn = (from.column + to.column) / 2
            val midCoordinate = Coordinate(midRow, midColumn)

            // Check if the intermediate square has an opponent's piece and that the destination square is empty
            val midPiece = board.getPiece(midCoordinate)

            if (midPiece != null && team != midPiece.team && board.getPiece(to) == null) {
                // The move is a valid capture
                return listOf(midCoordinate)
            }
        }
        // The move is not a valid capture
        return emptyList()
    }

    private fun getPotentialCaptures(coordinate: Coordinate, board: Board, team: Team): List<Pair<Coordinate, Coordinate>> {
        val potentialCaptures = mutableListOf<Pair<Coordinate, Coordinate>>()
        val directions = listOf(
            Pair(-2, -2), Pair(-2, 2), Pair(2, -2), Pair(2, 2)
        )

        for (direction in directions) {
            val potentialEndX = coordinate.row + direction.first
            val potentialEndY = coordinate.column + direction.second
            val potentialEndCoordinate = Coordinate(potentialEndX, potentialEndY)

            if(!board.isInBounds(potentialEndCoordinate)) continue

            val potentialMidX = coordinate.row + direction.first / 2
            val potentialMidY = coordinate.column + direction.second / 2
            val potentialMidCoordinate = Coordinate(potentialMidX, potentialMidY)

            if (isSingleCapture(coordinate, potentialEndCoordinate, board, team).isNotEmpty()) {
                potentialCaptures.add(Pair(potentialEndCoordinate, potentialMidCoordinate))
            }
        }

        return potentialCaptures
    }

    private fun getValidWithExecutionResult(captures: List<Coordinate>, from: Coordinate): ValidWithExecutionResult {
        val actions = mutableListOf<RemovePiece>()
        for (capture in captures) {
            actions.add(RemovePiece(getRelativePosition(from, capture)))
        }
        return ValidWithExecutionResult(actions)
    }

    private fun getRelativePosition(from: Coordinate, to: Coordinate): RelativePosition {
        return RelativePosition(abs(to.row - from.row), to.column - from.column)
    }

    private fun hasCapturesAvailable(to: Coordinate, board: Board, team: Team): Boolean {
        val potentialCaptures =  getPotentialCaptures(to, board, team)
        var atLeastOneForward = false
        if(team == Team.WHITE){
            for (potentialCapture in potentialCaptures) {
                if (potentialCapture.first.row > to.row) {
                    atLeastOneForward = true
                }
            }
        }else{
            for (potentialCapture in potentialCaptures) {
                if (potentialCapture.first.row < to.row) {
                    atLeastOneForward = true
                }
            }
        }
        return potentialCaptures.isNotEmpty() && atLeastOneForward
    }
}