package edu.austral.dissis.common.rule.obstacle

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule
class HorizontalObstacleValidator(
): Rule {

    override fun validateMove(move: Move, board: Board): RuleResult {
        // Check if move is not horizontal
        if(move.getFrom().row != move.getTo().row){
            return ValidResult
        }

        var currentCoordinate =
            if (move.getFrom().column < move.getTo().column){
                Coordinate(move.getFrom().row, move.getFrom().column + 1)
            }else {
                Coordinate(move.getFrom().row, move.getFrom().column - 1)
            }

        while (currentCoordinate.column != move.getTo().column){
            if (board.getPiece(currentCoordinate) != null){
                return InvalidResult()
            }

            currentCoordinate =
                if (currentCoordinate.column < move.getTo().column){
                    Coordinate(currentCoordinate.row, currentCoordinate.column + 1)
                }else {
                    Coordinate(currentCoordinate.row, currentCoordinate.column - 1)
                }
        }

        return ValidResult
    }
}
