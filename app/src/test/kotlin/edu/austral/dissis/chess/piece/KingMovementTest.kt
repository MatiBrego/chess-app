package edu.austral.dissis.chess.piece

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.result.move.PieceRuleViolationResult
import edu.austral.dissis.chess.result.move.SuccessfulResult
import edu.austral.dissis.chess.util.game.TestGameGenerator
import util.startingposition.king.KingInCenter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KingMovementTest {
    private val gameGenerator = TestGameGenerator()

    @Test
    fun `test valid king moves for king in center without obstacle`(){
        val game = gameGenerator.generate(KingInCenter(), emptyList())
        val kingPosition = Coordinate(3, 3)

        assertTrue(game.move(kingPosition, Coordinate(2, 3)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(4, 3)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(3, 2)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(3, 4)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(2, 2)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(4, 4)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(2, 4)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(4, 2)) is SuccessfulResult)
    }

    @Test
    fun `test invalid king moves for king in center without obstacle`(){
        val game = gameGenerator.generate(KingInCenter(), emptyList())
        val kingPosition = Coordinate(3, 3)

        assertTrue(game.move(kingPosition, Coordinate(3, 1)) is PieceRuleViolationResult)
        assertTrue(game.move(kingPosition, Coordinate(3, 5)) is PieceRuleViolationResult)
        assertTrue(game.move(kingPosition, Coordinate(5, 3)) is PieceRuleViolationResult)
        assertTrue(game.move(kingPosition, Coordinate(1, 3)) is PieceRuleViolationResult)

        assertTrue(game.move(kingPosition, Coordinate(1, 1)) is PieceRuleViolationResult)
    }
}
