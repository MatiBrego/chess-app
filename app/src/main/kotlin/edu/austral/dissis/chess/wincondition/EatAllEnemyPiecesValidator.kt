package edu.austral.dissis.chess.wincondition

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.rule.Rule

class EatAllEnemyPiecesValidator: WinningConditionValidator {
    override fun isWin(board: Board, enemyTeam: Team, gameRules: List<Rule>): Boolean {
        return board.getOccupiedPositions().none {
            board.getPiece(it)?.team == enemyTeam
        }
    }
}
