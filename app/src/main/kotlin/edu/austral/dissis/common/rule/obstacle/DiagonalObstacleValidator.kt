package edu.austral.dissis.common.rule.obstacle

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule
import kotlin.math.abs

class DiagonalObstacleValidator: Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        // Check if move is not diagonal
        if(abs(move.getFrom().row - move.getTo().row) != abs(move.getFrom().column - move.getTo().column)){
            return ValidResult
        }

        var currentCoordinate = move.getFrom()

        while (currentCoordinate.row != move.getTo().row && currentCoordinate.column != move.getTo().column){
            currentCoordinate =
                if (currentCoordinate.row < move.getTo().row && currentCoordinate.column < move.getTo().column){
                    Coordinate(currentCoordinate.row + 1, currentCoordinate.column + 1)
                }else if (currentCoordinate.row < move.getTo().row){
                    Coordinate(currentCoordinate.row + 1, currentCoordinate.column - 1)
                }else if (currentCoordinate.column < move.getTo().column){
                    Coordinate(currentCoordinate.row - 1, currentCoordinate.column + 1)
                }else {
                    Coordinate(currentCoordinate.row - 1, currentCoordinate.column - 1)
                }
            if (currentCoordinate.row != move.getTo().row && currentCoordinate.column != move.getTo().column && board.getPiece(currentCoordinate) != null){
                return InvalidResult()
            }
        }

        return ValidResult
    }
}
