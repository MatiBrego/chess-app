package edu.austral.dissis.chess.util.game.checkmate

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.wincondition.WinningConditionValidator
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.rule.Rule

class FakeValidator: WinningConditionValidator {
    override fun isWin(board: Board, enemyTeam: Team, gameRules: List<Rule>): Boolean {
        return false
    }
}
