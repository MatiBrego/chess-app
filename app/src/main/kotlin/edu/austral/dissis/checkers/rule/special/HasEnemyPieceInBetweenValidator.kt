package edu.austral.dissis.checkers.rule.special

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.action.Action
import edu.austral.dissis.common.result.action.RelativePosition
import edu.austral.dissis.common.result.action.RemovePiece
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule
import kotlin.math.abs

class HasEnemyPieceInBetweenValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        val midCoord = getMiddleCoordinate(move)
        val midPiece = board.getPiece(midCoord)

        return if (isPresentAndIsOpposingTeam(midPiece, move.getTurn()))
            ValidResult(getActions(move.getFrom(), midCoord))
        else
            InvalidResult()
    }

    private fun getMiddleCoordinate(move: Move): Coordinate{
        val midRow = (move.getFrom().row + move.getTo().row) / 2
        val midColumn = (move.getFrom().column + move.getTo().column) / 2
        return Coordinate(midRow, midColumn)
    }

    private fun isPresentAndIsOpposingTeam(piece: Piece?, team: Team): Boolean{
        return if (piece != null) {
            piece.team != team
        }else
            false
    }

    private fun getActions(from: Coordinate, coordinate: Coordinate): List<Action>{
        val relativeFrom = RelativePosition(abs(coordinate.row - from.row), coordinate.column - from.column)
        return listOf(
            RemovePiece(relativeFrom)
        )
    }
}