package edu.austral.dissis.chess.rule

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.RuleResult

interface Rule {
    fun validateMove(move: Move, board: Board): RuleResult
}
