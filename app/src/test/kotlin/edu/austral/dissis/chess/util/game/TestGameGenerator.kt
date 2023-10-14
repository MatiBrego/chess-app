package edu.austral.dissis.chess.util.game

import edu.austral.dissis.chess.board.NormalBoard
import edu.austral.dissis.chess.game.Game
import edu.austral.dissis.chess.game.checkmate.validator.FakeValidator
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.rule.Rule
import edu.austral.dissis.chess.startingposition.StartingPositionGenerator

class TestGameGenerator {
    fun generate(
        startingPositionGenerator: StartingPositionGenerator,
        rules: List<Rule> = emptyList()
        ): Game {
        return Game(
            NormalBoard(
            startingPositionGenerator.generate()),
            Team.WHITE,
            emptyMap(),
            rules,
            FakeValidator()
        )
    }
}
