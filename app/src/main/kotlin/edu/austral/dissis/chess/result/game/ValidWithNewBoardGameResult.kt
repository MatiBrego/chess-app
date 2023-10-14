package edu.austral.dissis.chess.result.game

import edu.austral.dissis.chess.board.Board
data class ValidWithNewBoardGameResult(val newBoard: Board): GameRuleResult {
}
