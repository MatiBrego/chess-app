package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Coordinate
import org.junit.jupiter.api.Test
import edu.austral.dissis.chess.result.move.GameRuleViolationResult
import edu.austral.dissis.chess.rule.game.IsOccupiedByTeamValidator
import edu.austral.dissis.chess.util.game.TestGameGenerator
import util.startingposition.rook.RookInCenterBlockedByTeam
import kotlin.test.assertTrue

class OccupiedRuleTest {
    private val gameGenerator = TestGameGenerator()
    @Test
    fun `test occupied coordinate`(){
        val game = gameGenerator.generate(
            RookInCenterBlockedByTeam(),
            listOf(IsOccupiedByTeamValidator())
        )

        val rookPosition = Coordinate(3,3)

        assertTrue(game.move(rookPosition, Coordinate(3,4)) is GameRuleViolationResult)
        assertTrue(game.move(rookPosition, Coordinate(3,2)) is GameRuleViolationResult)
        assertTrue(game.move(rookPosition, Coordinate(4,3)) is GameRuleViolationResult)
        assertTrue(game.move(rookPosition, Coordinate(2,3)) is GameRuleViolationResult)
    }
}
