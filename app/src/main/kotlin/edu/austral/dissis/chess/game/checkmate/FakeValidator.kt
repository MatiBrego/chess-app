package edu.austral.dissis.chess.game.checkmate

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.NormalBoard
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.rule.Rule

class FakeValidator: CheckMateValidator {
    override fun isCheckmate(board: Board, enemyTeam: Team, gameRules: List<Rule>): Boolean {
        return false
    }
}
