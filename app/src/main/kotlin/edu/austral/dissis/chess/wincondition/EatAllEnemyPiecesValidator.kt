package edu.austral.dissis.chess.wincondition

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.rule.Rule
import edu.austral.dissis.common.wincondition.WinningConditionValidator

class EatAllEnemyPiecesValidator: WinningConditionValidator {
    override fun isWin(board: Board, enemyTeam: Team, gameRules: List<Rule>): Boolean {
        return board.getOccupiedPositions().none {
            board.getPiece(it)?.team == enemyTeam
        }
    }
}
