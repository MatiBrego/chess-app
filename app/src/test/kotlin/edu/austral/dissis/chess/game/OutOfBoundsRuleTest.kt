package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.factory.createQueen
import edu.austral.dissis.chess.factory.queenInCenter
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.TestGameGenerator
import edu.austral.dissis.common.result.move.UnsuccessfulResult
import edu.austral.dissis.common.rule.game.OutOfBoundsValidator
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class OutOfBoundsRuleTest {
    private val gameGenerator = TestGameGenerator()
    @Test
    fun `test out of bounds`() {
        val game = gameGenerator.generate(queenInCenter(), rules = listOf(OutOfBoundsValidator()))
        val queenPosition = Coordinate(3, 3)

        assertTrue(game.move(queenPosition, Coordinate(9, 9)) is UnsuccessfulResult)
        assertTrue(game.move(queenPosition, Coordinate(9, 3)) is UnsuccessfulResult)
        assertTrue(game.move(queenPosition, Coordinate(3, 9)) is UnsuccessfulResult)
        assertTrue(game.move(queenPosition, Coordinate(-1, 3)) is UnsuccessfulResult)
        assertTrue(game.move(queenPosition, Coordinate(3, -1)) is UnsuccessfulResult)
        assertTrue(game.move(queenPosition, Coordinate(-1, -1)) is UnsuccessfulResult)
    }
}