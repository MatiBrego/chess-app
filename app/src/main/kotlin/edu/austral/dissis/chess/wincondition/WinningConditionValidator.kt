package edu.austral.dissis.chess.wincondition

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.rule.Rule

interface WinningConditionValidator {
    fun isWin(board: Board, enemyTeam: Team, gameRules: List<Rule>): Boolean
}
