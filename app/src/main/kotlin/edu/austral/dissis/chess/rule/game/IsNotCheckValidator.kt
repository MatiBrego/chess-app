package edu.austral.dissis.chess.rule.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.NormalBoard
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.*
import edu.austral.dissis.chess.rule.Rule

class IsNotCheckValidator: Rule {
    private val message = "That move leaves you in check"
    private val checkValidator = CheckValidator()
    override fun validateMove(move: Move, board: Board): RuleResult {
        val boardAfterMove = board.movePiece(move.getFrom(), move.getTo())

        if (checkValidator.isCheck(boardAfterMove, move.getTurn())) return InvalidResult(message)
        return ValidResult
    }
}
