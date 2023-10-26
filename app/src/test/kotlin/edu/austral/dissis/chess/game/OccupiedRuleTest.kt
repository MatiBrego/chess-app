package edu.austral.dissis.chess.game

import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.chess.factory.rookInCenterBlockedByTeam
import org.junit.jupiter.api.Test
import edu.austral.dissis.common.result.move.UnsuccessfulResult
import edu.austral.dissis.common.game.TestGameGenerator
import edu.austral.dissis.common.rule.game.IsOccupiedByTeamValidator
import kotlin.test.assertTrue

class OccupiedRuleTest {
    private val gameGenerator = TestGameGenerator()
    @Test
    fun `test occupied coordinate`(){
        val game = gameGenerator.generate(
            rookInCenterBlockedByTeam(),
            listOf(IsOccupiedByTeamValidator())
        )

        val rookPosition = Coordinate(3,3)

        assertTrue(game.move(rookPosition, Coordinate(3,4)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,2)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(4,3)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(2,3)) is UnsuccessfulResult)
    }
}
