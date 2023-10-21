package edu.austral.dissis.chess.rule.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class IsNotCheckValidator: Rule {
    private val message = "That move leaves you in check"
    private val checkValidator = CheckValidator()
    override fun validateMove(move: Move, board: Board): RuleResult {
        val boardAfterMove = board.movePiece(move.getFrom(), move.getTo())

        if (checkValidator.isCheck(boardAfterMove, move.getTurn())) return InvalidResult(message)
        return ValidResult
    }
}
