package edu.austral.dissis.chess.game.checkmate

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.NormalBoard
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.rule.Rule

interface CheckMateValidator {
    fun isCheckmate(board: Board, enemyTeam: Team, gameRules: List<Rule>): Boolean
}
