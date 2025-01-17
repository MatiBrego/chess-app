package edu.austral.dissis.common.rule.obstacle

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class VerticalObstacleValidator(
    private val isInclusive: Boolean = false
): Rule {

    override fun validateMove(move: Move, board: Board): RuleResult {
        // Check if move is not vertical
        if(move.getFrom().column != move.getTo().column){
            return ValidResult()
        }

        var currentCoordinate = if (move.getFrom().row < move.getTo().row){
            Coordinate(move.getFrom().row + 1, move.getFrom().column)
        }else {
            Coordinate(move.getFrom().row - 1, move.getFrom().column)
        }

        while (currentCoordinate.row != move.getTo().row){
            if (board.getPiece(currentCoordinate) != null){
                return InvalidResult()
            }
            currentCoordinate =
                if (currentCoordinate.row < move.getTo().row){
                    Coordinate(currentCoordinate.row + 1, currentCoordinate.column)
                }else {
                    Coordinate(currentCoordinate.row - 1, currentCoordinate.column)
                }
        }

        // If is inclusive is set to true, check if the "to" coordinate is occupied
        return if (!(isInclusive && board.getPiece(move.getTo()) != null))
            ValidResult()
        else
            InvalidResult()
    }
}
