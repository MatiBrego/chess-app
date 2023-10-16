package edu.austral.dissis.chess.util.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Game
import edu.austral.dissis.chess.game.checkmate.FakeValidator
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.rule.Rule
import edu.austral.dissis.chess.rule.game.PieceIsPresentValidator

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
