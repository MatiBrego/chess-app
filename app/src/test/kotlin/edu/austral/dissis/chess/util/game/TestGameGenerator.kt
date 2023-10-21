package edu.austral.dissis.chess.util.game

import edu.austral.dissis.chess.util.game.checkmate.FakeValidator
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.rule.Rule
import edu.austral.dissis.common.rule.game.PieceIsPresentValidator

class TestGameGenerator {
    fun generate(
        board: Board,
        rules: List<Rule> = emptyList()
        ): Game {
        return Game(
            board,
            Team.WHITE,
            listOf(PieceIsPresentValidator()) + rules,
            FakeValidator()
        )
    }
}
