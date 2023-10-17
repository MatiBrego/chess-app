package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.factory.kingInCenterWithRookAndEnemyRook
import edu.austral.dissis.chess.result.move.UnsuccessfulResult
import edu.austral.dissis.chess.rule.game.IsNotCheckValidator
import edu.austral.dissis.chess.util.game.TestGameGenerator
import kotlin.test.Test
import kotlin.test.assertTrue

class LeavesInCheckRuleTest {
    private val gameGenerator = TestGameGenerator()

    @Test
    fun `test leaves in check after team move`(){
        val game = gameGenerator.generate(
            kingInCenterWithRookAndEnemyRook(),
            listOf(
                IsNotCheckValidator()
            )
        )

        assertTrue(game.move(Coordinate(4, 3), Coordinate(4, 4)) is UnsuccessfulResult)
    }
}
