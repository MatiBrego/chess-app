package edu.austral.dissis.chess.util.game.checkmate

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.wincondition.WinningConditionValidator
import edu.austral.dissis.common.piece.Team

class FakeValidator: WinningConditionValidator {
    override fun isWin(board: Board, enemyTeam: Team, gameRules: List<edu.austral.dissis.common.rule.Rule>): Boolean {
        return false
    }
}
