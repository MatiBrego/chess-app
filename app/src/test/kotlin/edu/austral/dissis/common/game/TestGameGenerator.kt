package edu.austral.dissis.common.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.wincondition.FakeValidator
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.rule.Rule
import edu.austral.dissis.common.rule.game.PieceIsPresentValidator
import edu.austral.dissis.common.turn.OneEach
import edu.austral.dissis.common.turn.Turn

class TestGameGenerator {
    fun generate(
        board: Board,
        rules: List<Rule> = emptyList(),
        turnManager: Turn = OneEach(Team.WHITE)
        ): Game {
        return Game(
            board,
            turnManager,
            listOf(PieceIsPresentValidator()) + rules,
            FakeValidator()
        )
    }
}
