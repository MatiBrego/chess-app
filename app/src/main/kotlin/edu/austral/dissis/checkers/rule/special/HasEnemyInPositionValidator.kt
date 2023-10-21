package edu.austral.dissis.checkers.rule.special

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.action.RelativePosition
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class HasEnemyInPositionValidator(
    private val relativePosition: RelativePosition
): Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        val from = move.getFrom();

        val absolutePosition = calculateAbsolutePosition(from);

        if (!hasPieceInPosition(board, absolutePosition)) {
            return InvalidResult("There is no enemy piece in position $absolutePosition")
        }
        return ValidResult
    }

    private fun calculateAbsolutePosition(position: Coordinate): Coordinate {
        return Coordinate(
            position.row + relativePosition.getRow(),
            position.column + relativePosition.getColumn()
        );
    }

    private fun hasPieceInPosition(board: Board, position: Coordinate): Boolean {
        return board.getPiece(position) != null;
    }
}