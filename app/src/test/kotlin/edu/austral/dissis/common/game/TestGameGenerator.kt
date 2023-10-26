package edu.austral.dissis.common.game

import edu.austral.dissis.common.game.wincondition.FakeValidator
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.rule.Rule
import edu.austral.dissis.common.rule.game.PieceIsPresentValidator

class TestGameGenerator {
    fun generate(
        board: Board,
        rules: List<Rule> = emptyList(),
        turn: Team = Team.WHITE
        ): Game {
        return Game(
            board,
            turn,
            listOf(PieceIsPresentValidator()) + rules,
            FakeValidator()
        )
    }
}
